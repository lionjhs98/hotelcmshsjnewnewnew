<template>
    <v-app id="inspire">
      <div>
        <!-- App Bar -->
        <v-app-bar color="primary" app clipped-left flat>
          <!-- 네비게이션 아이콘 -->
          <v-app-bar-nav-icon
            @click="openSideBar()"
            style="z-index: 1; height: 56px; width: 30px; margin-right: 10px; font-weight: 300; font-size: 55px;"
          >
            <div style="line-height: 100%;">≡</div>
          </v-app-bar-nav-icon>
  
          <!-- 가운데로 정렬된 제목 -->
          <v-spacer></v-spacer>
          <v-toolbar-title class="header-title">Hotel CMS</v-toolbar-title>
          <v-spacer></v-spacer>
  
          <!-- 홈 아이콘 -->
          <span
            v-if="urlPath!=null"
            class="mdi mdi-home"
            @click="goHome()"
            style="margin-left: 10px; font-size: 20px; cursor: pointer;"
          ></span>
        </v-app-bar>
  
        <!-- Navigation Drawer (Sidebar) -->
        <v-navigation-drawer app clipped flat v-model="sideBar">
          <!-- Host 기능 섹션 -->
          <v-list-item-group>
            <v-list-item-title class="sidebar-title">Host 기능</v-list-item-title>
            <v-list-item
              v-for="(item, index) in hostFunctions"
              :key="index"
              :to="item.route"
              @click="changeUrl()"
              class="px-2 sidebar-item"
            >
              {{ item.title }}
            </v-list-item>
          </v-list-item-group>
  
          <!-- 구분선 추가 -->
          <v-divider></v-divider>
  
          <!-- HouseKeeper 기능 섹션 -->
          <v-list-item-group>
            <v-list-item-title class="sidebar-title">HouseKeeper 기능</v-list-item-title>
            <v-list-item
              v-for="(item, index) in houseKeeperFunctions"
              :key="index"
              :to="item.route"
              @click="changeUrl()"
              class="px-2 sidebar-item"
            >
              {{ item.title }}
            </v-list-item>
          </v-list-item-group>
        </v-navigation-drawer>
      </div>
  
      <!-- Main Content -->
      <v-main>
        <v-container style="padding: 0px;" v-if="urlPath" fluid>
          <router-view></router-view>
        </v-container>
        <v-container style="padding: 20px;" v-else fluid>
          <!-- Host 기능 섹션 -->
          <v-row
            class="section-container"
            style="margin-bottom: 40px; padding: 20px; border: 1px solid #ddd; border-radius: 8px; background-color: #f9f9f9;"
          >
            <v-col cols="12">
              <h2 class="section-title">Host 기능</h2>
            </v-col>
            <v-col cols="4" v-for="(item, index) in hostFunctions" :key="index">
              <v-card outlined class="d-flex flex-column align-center pa-4" @click="changeUrl()" :to="item.route">
                <v-icon large class="mb-3" color="primary">{{ item.icon }}</v-icon>
                <h3 class="text-h6">{{ item.title }}</h3>
                <p>{{ item.description }}</p>
                <v-btn text color="primary">{{ item.buttonText }}</v-btn>
              </v-card>
            </v-col>
          </v-row>
  
          <!-- HouseKeeper 기능 섹션 -->
          <v-row
            class="section-container"
            style="padding: 20px; border: 1px solid #ddd; border-radius: 8px; background-color: #f9f9f9;"
          >
            <v-col cols="12">
              <h2 class="section-title">HouseKeeper 기능</h2>
            </v-col>
            <v-col cols="4" v-for="(item, index) in houseKeeperFunctions" :key="index">
              <v-card outlined class="d-flex flex-column align-center pa-4" @click="changeUrl()" :to="item.route">
                <v-icon large class="mb-3" color="primary">{{ item.icon }}</v-icon>
                <h3 class="text-h6">{{ item.title }}</h3>
                <p>{{ item.description }}</p>
                <v-btn text color="primary">{{ item.buttonText }}</v-btn>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
      </v-main>
    </v-app>
  </template>
  
  <script>
  export default {
    name: "SNSApp",
    data: () => ({
      sideBar: true,
      urlPath: null,
      hostFunctions: [
        { title: '숙박 관리', description: '객실별 체크인/체크아웃 상태 관리', route: '/reservations/accomodations', icon: 'mdi-bed', buttonText: '숙박 정보 등록/확인' }, // 버튼 이름 설정
        { title: '하우스키퍼 배정하기', description: '체크인된 객실에 하우스키퍼 배정', route: '/assigningstatuses/assignHouseKeepers', icon: 'mdi-account-group', buttonText: '배정하기' },
        { title: '객실 관리', description: '객실번호/타입/숙박상태/청소여부 등 정보 관리', route: '/rooms/rooms', icon: 'mdi-door', buttonText: '객실 등록/확인' }
      ],
      houseKeeperFunctions: [
        { title: '청소 상태 변경', description: '청소 완료 시 청소 상태 업데이트', route: '/cleaningstatuses/cleaningStatuses', icon: 'mdi-broom', buttonText: '상태 업데이트' }
      ]
    }),
    created() {
      var path = document.location.href.split("#/");
      this.urlPath = path[1];
    },
    methods: {
      openSideBar() {
        this.sideBar = !this.sideBar;
      },
      changeUrl() {
        var path = document.location.href.split("#/");
        this.urlPath = path[1];
      },
      goHome() {
        this.urlPath = null;
      }
    }
  };
  </script>
  
  <style>
  .header-title {
    font-size: 2rem; /* 제목 크기 확대 */
    font-weight: bold; /* 텍스트 두껍게 */
    color: white; /* 글자 색상 */
    text-align: center; /* 텍스트 중앙 정렬 */
  }
  
  .section-container {
    margin-bottom: 20px;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 8px;
    background-color: #f9f9f9;
  }
  .section-title {
    font-size: 1.5rem;
    font-weight: bold;
    margin-bottom: 20px;
  }
  .sidebar-title {
    font-size: 1.1rem;
    font-weight: bold;
    margin: 10px 0;
  }
  .sidebar-item {
    font-size: 1rem;
    margin-left: 10px;
  }
  </style>
  
  
  
  
  
  