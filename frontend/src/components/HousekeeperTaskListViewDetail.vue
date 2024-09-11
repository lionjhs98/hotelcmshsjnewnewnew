<template>

    <v-card outlined>
        <v-card-title>
            HousekeeperTaskList # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="HousekeepingId" v-model="item.housekeepingId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="HousekeeperId" v-model="item.housekeeperId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="AccomodationId" v-model="item.accomodationId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Boolean label="Cleaned" v-model="item.cleaned" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="RoomId" v-model="item.roomId" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'HousekeeperTaskListViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/housekeeperTaskLists/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

