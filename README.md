# Hotel 관리시스템 프로젝트

## 프로젝트 개요
**Hotel 관리시스템**는 KT의 **GIGA Genie HOTEL** 서비스를 개선하기 위해 개발된 호텔 관리 시스템입니다. 이 시스템은 호텔 관리자가 체크인/체크아웃 내역을 등록하고, 청소 인력(하우스키퍼)을 객실에 배정하고, 배정된 인력이 청소를 완료하면 상태를 업데이트할 수 있도록 했습니다. 

기존에는 단순히 호텔 관리자만이 시스템에 접속해서 호텔의 청소 상태를 변경할 수 있었지만, <u>**호텔의 청소 업무를 수정하는 Housekeeper인력 또한 시스템에 접속해 직접 호텔 청소 완료 상태를 업데이트할 수 있는 기능**</u>을 추가하였습니다. 

이를 통해 관리자는 Housekeeper가 등록한 청소 완료 상태를 실시간으로 확인할 수 있습니다. 

## 주요 기능

### 1. Host 기능
- **객실 정보 관리**: 호텔 관리자는 각 객실의 기본 정보(청소 상태, 체크인 상태 등)를 확인할 수 있으며, 정보가 실시간으로 반영됩니다.
- **체크인 정보 등록**: 호텔 관리자는 고객의 체크인 정보를 등록할 수 있으며, 이 과정에서 체크인 시간이 시스템에 반영되고 객실의 체크인 상태가 업데이트됩니다.
- **체크아웃 정보 등록**: 호텔 관리자는 고객의 체크아웃 정보를 등록할 수 있으며, 체크아웃 시간이 시스템에 반영됩니다.
- **예약 상태 조회**: 호텔 관리자는 숙소의 예약 상태(객실 ID, 체크인/체크아웃 시간 등)를 실시간으로 조회할 수 있습니다.
- **하우스키퍼 배정**: 호텔 관리자는 특정 객실에 하우스키퍼를 배정하고, 필요 시 이를 수정할 수 있습니다.

### 2. Housekeeper 기능
- **배정 내역 확인**: 하우스키퍼는 자신에게 배정된 객실 목록과 상태를 조회할 수 있습니다.
- **청소 상태 업데이트**: 청소가 완료된 후 하우스키퍼는 시스템에 청소 상태를 업데이트하여 실시간으로 반영합니다.

## 비기능적 요구사항

### 1. 트랜잭션 관리
- **예약 트랜잭션 처리**: 청소가 완료되지 않은 방은 예약할 수 없으며, 청소 상태를 즉시 확인한 후 예약이 가능해야 합니다.
- **즉시성 보장**: 체크인 및 청소 완료 시 객실의 상태가 즉각적으로 업데이트되어야 하며, 이를 통해 사용자에게 최신 정보를 제공하고 오류를 방지합니다.

### 2. 데이터 조회 성능
- **정보 일괄 조회**: 시스템은 모든 방의 청소 상태, 체크인 상태 등의 정보를 한 번에 확인할 수 있도록 지원해야 합니다. 효율적인 데이터 구조와 인덱싱을 통해 조회 성능을 최적화해야 합니다.
- **실시간 정보 반영**: 청소 상태나 체크인 상태가 변경되면 그 정보가 실시간으로 반영되어야 하며, 사용자에게 최신 정보를 제공할 수 있도록 **이벤트 기반 아키텍처**를 적용합니다.


## 이벤트 스토밍 다이어그램

다음은 MSA에서 **이벤트 스토밍**을 통해 각 모듈 간 이벤트 흐름을 시각화한 다이어그램입니다.

![이벤트스토밍](https://github.com/user-attachments/assets/2ead8fdb-10d7-40bc-8a00-84606dcc664b)

### Hotel CMS 비즈니스 로직 흐름

1. **메뉴 - 객실 등록** : 호스트가 room을 등록한다(crud 중 c)
2. **메뉴 - 숙박 등록** : 호스트가 존재하는 객실들에 대해 숙박 내역을 등록한다(crud 중 c) - accomodationId, roomId, 체크인/아웃 시간 (registerCheckInInfo -> CheckInInfoRegistered)
3. **메뉴 - 객실 등록** : 호스트가 숙박 내역을 등록하면(CheckInInfoRegistered), 등록된 객실의 checkedIn 상태가 true로, cleaned 상태가 false로 자동으로 바뀐다. (RoomStatusUpdate)
4. **메뉴 - 하우스키퍼 배정** : 숙박을 등록하는 동시에, 하우스키퍼 배정 메뉴에 카드가 생성된다(각 room에 대한) (Req/Res)
5. **메뉴 - 하우스키퍼 배정** : 호스트는 생성된 카드에 수정을 눌러서 하우스키퍼를 배정한다(하우스키퍼 id 작성) (assignHouseKeeper -> HouseKeeperAssigned)
6. **메뉴 - 청소 상태 변경** : 하우스키퍼가 배정되면, 청소상태 변경 메뉴에서 자동으로 내역을 확인할 수 있다 (updateAssigningStatus) -> 근데 내역이 안뜸. 로직은 작성되어 있고, 프론트 문제인것으로 보임
7. **메뉴 - 청소 상태 변경** : 하우스키퍼는 청소를 완료하면, 생성된 내역(카드)에서 수정 버튼을 눌러서 청소 상태를 완료로 변경(true)할 수 있다. (updateCleaningStatus -> CleaningStatusUpdated)
8. **메뉴 - 객실 등록** : 하우스키퍼가 청소 상태를 변경하면, 자동으로 객실(Room)의 cleaned 속성이 false에서 true로 바뀐다. (RoomCleanUpdate)





## 시스템 아키텍처

### 헥사고날 아키텍처 (Hexagonal Architecture)
이 프로젝트는 **헥사고날 아키텍처**를 기반으로 설계되었습니다. 각 모듈은 독립적으로 동작하며, 이벤트 기반으로 상호작용하여 효율적인 데이터 처리를 보장합니다. **CQRS**와 **Event Driven Architecture** 패턴을 적용하여 성능 최적화 및 실시간 데이터 동기화를 구현하였습니다.

### 아키텍처 다이어그램
다음은 시스템의 **MSA 기반 이벤트 스토밍**을 반영한 헥사고날 아키텍처 다이어그램입니다.

![헥사고날](https://github.com/user-attachments/assets/4a0ba2e8-006f-4351-ab8a-a1da140bec9a)


### 이벤트 흐름
시스템의 주요 이벤트 흐름은 다음과 같습니다.

- **CheckInInfoRegistered 이벤트**: 체크인 정보가 등록되면 객실의 상태가 즉시 업데이트됩니다.
- **CheckoutInfoRegistered 이벤트**: 체크아웃 정보가 등록되면 객실의 상태가 즉시 반영되며, 해당 정보가 Host 및 Housekeeper에게 실시간으로 제공됩니다.
- **HousekeeperAssigned 이벤트**: 하우스키퍼가 특정 객실에 배정되면, Host 및 Housekeeper는 배정된 정보를 실시간으로 확인할 수 있습니다.
- **CleaningStatusUpdated 이벤트**: 하우스키퍼가 청소 상태를 업데이트하면, Host는 그 정보를 실시간으로 확인할 수 있습니다.



## 실행 방법
### 로컬
각 BC별로 대변되는 마이크로 서비스들을 스프링부트로 구현하였습니다. 구현한 각 서비스들의 포트넘버는 아래와 같습니다.

```
      routes:
        - id: reservation
          uri: http://localhost:8082
          predicates:
            - Path=/accomodations/**, 
        - id: assigningstatus
          uri: http://localhost:8083
          predicates:
            - Path=/assignHouseKeepers/**, 
        - id: room
          uri: http://localhost:8084
          predicates:
            - Path=/rooms/**, 
        - id: cleaningstatus
          uri: http://localhost:8085
          predicates:
            - Path=/cleaningStatuses/**, 
        - id: frontend
          uri: http://localhost:8080
          predicates:
            - Path=/**
```
서비스들의 실행 방법은 아래와 같습니다.
```
mvn spring-boot:run
```
## CQRS
**1. 숙박 정보 조회**

Host가 숙박 정보를 등록하면, 내역을 조회할 수 있도록 CQRS로 구현하였습니다. 
- 비동기식으로 처리되어 발행된 이벤트 기반 Kafka를 통해 수신/처리되어 별도 Table에 관리합니다.
- Table 모델링

| Column | Description | Type |
| --- | --- | --- |
| accomdationId | 숙박ID | Long |
| roomId | 객실ID | Long |
| checkInTime | 체크인 날짜 | Date |
| checkOutTime | 체크아웃 날짜 | Date |

ReservationStatusViewHandler.java 통해 구현

    @Autowired
    private ReservationStatusRepository reservationStatusRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCheckInInfoRegistered_then_CREATE_1(
        @Payload CheckInInfoRegistered checkInInfoRegistered
    ) {
        try {
            if (!checkInInfoRegistered.validate()) return;

            // view 객체 생성
            ReservationStatus reservationStatus = new ReservationStatus();
            // view 객체에 이벤트의 Value 를 set 함
            reservationStatus.setAccomodationid(
                checkInInfoRegistered.getAccomodationId()
            );
            reservationStatus.setRoomId(checkInInfoRegistered.getRoomId());

            Date utilDate = checkInInfoRegistered.getCheckInTime(); // Date 타입의 변수
            reservationStatus.setCheckInTime(utilDate);

            // view 레파지 토리에 save
            reservationStatusRepository.save(reservationStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

**2. 하우스키퍼 배정 정보 조회**

Host가 하우스키퍼를 배정하면, 내역을 조회할 수 있도록 구현했습니다.
- Table 모델링

| Column | Description | Type |
| --- | --- | --- |
| housekeepingId | 하우스키핑ID | Long |
| housekeeperId | 하우스키퍼ID | Long |
| accomdationId | 숙박ID | Long |
| roomId | 객실ID | Long |

HousekeepingAssignmentInformationViewHandler.java 통해 구현

    @Autowired
    private HousekeepingAssignmentInformationRepository housekeepingAssignmentInformationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenHousekeeperAssigned_then_CREATE_1 (@Payload HousekeeperAssigned housekeeperAssigned) {
        try {

            if (!housekeeperAssigned.validate()) return;

            // view 객체 생성
            HousekeepingAssignmentInformation housekeepingAssignmentInformation = new HousekeepingAssignmentInformation();
            // view 객체에 이벤트의 Value 를 set 함
            housekeepingAssignmentInformation.setHousekeepingId(housekeeperAssigned.getHousekeepingId());
            housekeepingAssignmentInformation.setHousekeeperId(housekeeperAssigned.getHousekeeperId());
            housekeepingAssignmentInformation.setAccomodationId(housekeeperAssigned.getAccomodationId());
            // view 레파지 토리에 save
            housekeepingAssignmentInformationRepository.save(housekeepingAssignmentInformation);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

**3. 하우스키퍼 청소 업무 조회**

Housekeeper가 해야 하는 청소 업무를 확인할 수 있도록 구현했습니다.
- Table 모델링

| Column | Description | Type |
| --- | --- | --- |
| housekeepingId | 하우스키핑ID | Long |
| accomdationId | 숙박ID | Long |
| housekeeperId | 하우스키퍼ID | Long |
| cleaned | 청소여부 | Boolean |
| roomId | 객실ID | Long |

HousekeeperTaskListViewHandler.java 통해 구현

    @Autowired
    private HousekeeperTaskListRepository housekeeperTaskListRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenHousekeeperAssigned_then_CREATE_1 (@Payload HousekeeperAssigned housekeeperAssigned) {
        try {

            if (!housekeeperAssigned.validate()) return;

            // view 객체 생성
            HousekeeperTaskList housekeeperTaskList = new HousekeeperTaskList();
            // view 객체에 이벤트의 Value 를 set 함
            housekeeperTaskList.setHousekeepingId(housekeeperAssigned.getHousekeepingId());
            housekeeperTaskList.setHousekeeperId(housekeeperAssigned.getHousekeeperId());
            housekeeperTaskList.setAccomodationId(housekeeperAssigned.getAccomodationId());
            // view 레파지 토리에 save
            housekeeperTaskListRepository.save(housekeeperTaskList);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


### 동기식 호출(Sync, Feign Client)
동기식 호출을 하는 경우는 Reservation에서 발생한 이벤트를 통해 assignstatus의 하우스키퍼를 배정하는 이벤트가 생성되는 경우입니다. 동기식 호출을 사용하면 청소부가 정상적으로 배정되었는지 실시간으로 확인할 수 있으며, 문제가 발생할 경우 즉시 대응할 수 있습니다. 이러한 방식은 특히 예약이 확정되기 전에 모든 관련 작업이 완료되어야 하는 시나리오에서 유리하며, 트랜잭션의 일관성을 보장하여 예약과 청소부 배정이 올바르게 처리되도록 돕습니다.

1. **(Reservation -> assigningstatus)**
> Reservation에서 객실 예약을 생성하면, Room에 저장되어있는 객실이 Checkin 완료, 및 Clean이 필요한 상태로 업데이트되어야 합니다. 

아래의 코드는 @FeignClient를 통해 Reservation 서비스에서 assigningstatus를 연결한 코드입니다.
```
@FeignClient(name = "assigningstatus", url = "${api.url.assigningstatus}")
public interface AssignHouseKeeperService {
    @RequestMapping(method = RequestMethod.POST, path = "/assignHouseKeepers")
    public void assignHousekeeper(
        @RequestBody AssignHouseKeeper assignHouseKeeper
    );
}
```
위의 코드를 통해 두가지의 서비스를 연결한 후 "객실 예약"이라는 이벤트가 reservation 서비스에서 일어남과 동시에 assigningstatus에서 Host가 객실에 청소부를 배정할 수 있는 기능이 생성이 됩니다.

```
hotelcmshsjnewnew.external.AssignHouseKeeper assignHouseKeeper = new hotelcmshsjnewnew.external.AssignHouseKeeper();
        assignHouseKeeper.setAccomodationId(accomodationId);
        assignHouseKeeper.setRoomId(roomId);

```


### 비동기식 호출(Kafka)
비동기식 호출을 하는 경우는 CheckInfoRegistered(Reservation -> Room), HousekeeperAssigned(AsssigningStatus->CleaningStatus), CleaningStatusUpdated(CleaningStatus -> Room) 세 가지 경우입니다. 
<u>**비동기식 통신은 각 서비스가 독립적으로 동작할 수 있으며, 응답을 기다릴 필요가 없고, 최종 일관성만 보장되면 되는 경우에 적합하기 때문에 세 가지 경우를 비동기식 호출로 구성**</u>했습니다.

1. **CheckInfoRegistered(Reservation -> Room)**
> Reservation에서 객실 예약을 생성하면, Room에 저장되어있는 객실이 Checkin 완료, 및 Clean이 필요한 상태로 업데이트되어야 합니다. 즉각적인 응답이 필요 없고, 각 서비스는 독립적으로 동작할 수 있기 때문에 비동기 통신 방식을 선택했습니다.
- Room / infra / **PolicyHandler.java**
```
@StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CheckInInfoRegistered'"
    )
    public void wheneverCheckInInfoRegistered_CheckinStatusUpdate(
        @Payload CheckInInfoRegistered checkInInfoRegistered
    ) {
        CheckInInfoRegistered event = checkInInfoRegistered;
        System.out.println(
            "\n\n##### listener CheckinStatusUpdate : " +
            checkInInfoRegistered +
            "\n\n"
        );

        // Sample Logic //
        Room.checkinStatusUpdate(event);
    }
```
- Room / domain / **Room.java**
```
public static void checkinStatusUpdate(
        CheckInInfoRegistered checkInInfoRegistered
    ) { 
        System.out.println("CheckInInfoRegistered Object" + checkInInfoRegistered.toString());
        repository().findById(checkInInfoRegistered.getRoomId()).ifPresent(room -> {
            room.setCheckedIn(true); // 체크인 상태 업데이트
            room.setCleaned(false); // 청소 상태 업데이트
            System.out.println("checkInInfoRegistered : " + room.toString());
            repository().save(room);
        });

    }
```

2. **HousekeeperAssigned(AsssigningStatus->CleaningStatus)**
> 호스트가 AssigningStatus에서 하우스키퍼를 배정하면, 하우스키퍼가 확인할 수 있는 페이지인 CleaningStatus에 배정 정보가 새롭게 생성되어야 합니다. 즉각적인 응답이 필요 없고, 각 서비스는 독립적으로 동작할 수 있기 때문에 비동기 통신 방식을 선택했습니다.
- Cleaningstatus / infra / **PolicyHandler.java**
```
@StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='HousekeeperAssigned'"
    )
    public void wheneverHousekeeperAssigned_UpdateAssigningStatus(
        @Payload HousekeeperAssigned housekeeperAssigned
    ) {
        HousekeeperAssigned event = housekeeperAssigned;
        System.out.println(
            "\n\n##### listener UpdateAssigningStatus : " +
            housekeeperAssigned +
            "\n\n"
        );

        // Sample Logic //
        CleaningStatus.updateAssigningStatus(event);
    }
```
- Cleaningstatus / domain / CleaningStatus.java
```
public static void updateAssigningStatus(
        HousekeeperAssigned housekeeperAssigned
    ) {
        //implement business logic here:

        CleaningStatus cleaningStatus = new CleaningStatus();
        cleaningStatus.accomodationId = housekeeperAssigned.getAccomodationId();
        cleaningStatus.housekeeperId = housekeeperAssigned.getHousekeeperId();
        cleaningStatus.roomId = housekeeperAssigned.getRoomId();
        cleaningStatus.cleaned = false;
        repository().save(cleaningStatus);

    }
```


3. **CleaningStatusUpdated(CleaningStatus -> Room)**
> 하우스키퍼가 CleaningStatus에서 자신에게 배정된 청소건을 완료하고 청소완료로 상태를 수정하면, Room에 저장되어있는 해당 객실이 Clean이 완료된 상태로 업데이트되어야 합니다. 즉각적인 응답이 필요 없고, 각 서비스는 독립적으로 동작할 수 있기 때문에 비동기 통신 방식을 선택했습니다.
- Room / infra / **PolicyHandler.java**
```
@StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CleaningStatusUpdated'"
    )
    public void wheneverCleaningStatusUpdated_CleaningStatusUpdate(
        @Payload CleaningStatusUpdated cleaningStatusUpdated
    ) {
        CleaningStatusUpdated event = cleaningStatusUpdated;
        System.out.println(
            "\n\n##### listener CleaningStatusUpdate : " +
            cleaningStatusUpdated +
            "\n\n"
        );

        // Sample Logic //
        Room.cleaningStatusUpdate(event);
    }
```
- Room / domain / **Room.java**
```
public static void cleaningStatusUpdate(
        CleaningStatusUpdated cleaningStatusUpdated
    ) {
        System.out.println("CleaningStatusUpdated Object" + cleaningStatusUpdated.toString());
        repository().findById(cleaningStatusUpdated.getRoomId()).ifPresent(room -> {
            room.setCleaned(true); // 청소 상태 업데이트
            System.out.println("cleaningStatusUpdated : " + room.toString());
            repository().save(room);
        });
    }
```

## AKS 및 Docker 환경 설정

**1. 리소스 그룹 생성 및 AKS 생성**
- 리소스 그룹명: user05-rsrcgrp
- 클러스터명: user55-aks
 
**2. Azure CLI 환경 설정**
- Azure Client 설치 및 Credential 설정
```
curl -sL https://aka.ms/InstallAzureCLIDeb | sudo bash 
```
- Kubernetes Client 설정
```
az aks get-credentials --resource-group user05-rsrcgrp --name user55-aks
```
**3. Docker hub 설정**

먼저, 도커허브를 회원가입 한 후 계정명과 비밀번호를 기록해둡니다. 

이후에, 터미널을 배포하고자 하는 소스 파일의 터미널을 오픈하여 아래의 문구를 작성합니다. 

```
Docker login
```

이후에 기록해둔 계정명과 패스워드를 입력하여 설정을 마무리합니다.



## Docer Build 이미지 생성 및 배포

**1. Docker Build 이미지 생성** 

본격적으로 도커 및 AKS 환경 설정이 끝났다면, 모든 서비스에서 대해 도커 이미지를 생성(도커라이징)하고 저장소에 Push를 해야합니다.


---

우선, **프론트엔트**는 도커빌드 후 쿠버네티스에 반영하기 위해 다음 명령어를 실행합니다.

```
npm run build
docker build -t hueylee/ui:stable .
docker push hueylee/ui:stable
```


그 다음으로 마이크로서비스들이 EDA 통신하기 위한 Kafka 서버를 내 클러스터에 설치야 합니다. 

예를 들어, **객실(Room) 서비스**의 경우, 다음과 같은 명령어를 실행합니다. 

```
     cd room
mvn package -B -Dmaven.test.skip=true

```

**2. Docker Build 이미지 배포** 

이후에 최상위 root 에 Dockerfile이 있는지 확인한 후, Dockerfile 파일이 있는 프로젝트 루트에서 아래 명령을 실행하여 도커라이징을 진행합니다.

```
docker build -t hueylee/room:배포날짜 .     
docker image ls
docker push hueylee/room:배포날짜 .     
```

이와 같은 방식으로, reservation, gateway, assigningstatus, cleaningstatus 도 각각 폴더별로 동일하게 도커 이미지를 생성하고 저장소에 Push하며 도커라이징을 진행합니다. 

---
결과적으로, 다음 이미지와 같이 도커 허브에 이미지가 배포됩니다. 



## 클러스터 배포

**1. 클러스터에 Event Store(kafka) 설치**

Helm(패키지 인스톨러) 설치
 
```
curl https://raw.githubusercontent.com/helm/helm/master/scripts/get-helm-3 > get_helm.sh
chmod 700 get_helm.sh
./get_helm.sh    
```

helm으로 Kafka 설치

```
helm repo add bitnami https://charts.bitnami.com/bitnami
helm repo update
helm install my-kafka bitnami/kafka --version 23.0.5
```

**2. 객실관리 서비스 yaml로 배포**
- room/kubernetes 폴더내의 deployment.yaml을 오픈한다.
- image: push 한 이미지명으로 수정한다:
hueylee/room:240913
- 저장 후, apply 진행한다. 
```
kubectl apply -f kubernetes/deployment.yaml 

kubectl apply -f kubernetes/service.yaml
```
- 배포 확인
```
kubectl get all
```

**3. reservation, gateway, assigningstatus, cleaningstatus, frontend도 동일작업 수행**
- 다른 쿠버네티스의 폴더의 deployment 및 service 파일의 확장자가 yml, yaml 인지를 잘 확인하며 배포를 마무리합니다. 
- 전부 배포가 완료되었다면 게이트 주소를 확인합니다. 
```
kubectl get svc
```

**4. 게이트 주소로 이동**
- 이동 결과 개발한 호텔 객실 관리 서비스를 확인할 수 있습니다. 
