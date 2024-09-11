<template>

    <v-data-table
        :headers="headers"
        :items="housekeeperTaskList"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'HousekeeperTaskListView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "housekeepingId", value: "housekeepingId" },
                { text: "housekeeperId", value: "housekeeperId" },
                { text: "accomodationId", value: "accomodationId" },
                { text: "cleaned", value: "cleaned" },
                { text: "roomId", value: "roomId" },
            ],
            housekeeperTaskList : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/housekeeperTaskLists'))

            temp.data._embedded.housekeeperTaskLists.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.housekeeperTaskList = temp.data._embedded.housekeeperTaskLists;
        },
        methods: {
        }
    }
</script>

