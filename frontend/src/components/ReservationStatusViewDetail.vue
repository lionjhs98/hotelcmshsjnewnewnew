<template>

    <v-card outlined>
        <v-card-title>
            ReservationStatus # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="Accomodationid" v-model="item.accomodationid" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="RoomId" v-model="item.roomId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Date label="CheckInTime" v-model="item.checkInTime" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Date label="CheckOutTime" v-model="item.checkOutTime" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'ReservationStatusViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/reservationStatuses/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

