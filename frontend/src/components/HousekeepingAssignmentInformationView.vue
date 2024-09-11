<template>

    <v-data-table
        :headers="headers"
        :items="housekeepingAssignmentInformation"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'HousekeepingAssignmentInformationView',
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
                { text: "roomId", value: "roomId" },
            ],
            housekeepingAssignmentInformation : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/housekeepingAssignmentInformations'))

            temp.data._embedded.housekeepingAssignmentInformations.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.housekeepingAssignmentInformation = temp.data._embedded.housekeepingAssignmentInformations;
        },
        methods: {
        }
    }
</script>

