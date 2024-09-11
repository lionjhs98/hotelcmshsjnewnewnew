<template>

    <v-data-table
        :headers="headers"
        :items="reservationStatus"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'ReservationStatusView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "accomodationid", value: "accomodationid" },
                { text: "roomId", value: "roomId" },
                { text: "checkInTime", value: "checkInTime" },
                { text: "checkOutTime", value: "checkOutTime" },
            ],
            reservationStatus : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/reservationStatuses'))

            temp.data._embedded.reservationStatuses.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.reservationStatus = temp.data._embedded.reservationStatuses;
        },
        methods: {
        }
    }
</script>

