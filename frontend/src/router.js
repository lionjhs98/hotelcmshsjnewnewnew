
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import ReservationAccomodationManager from "./components/listers/ReservationAccomodationCards"
import ReservationAccomodationDetail from "./components/listers/ReservationAccomodationDetail"

import AssigningstatusAssignHouseKeeperManager from "./components/listers/AssigningstatusAssignHouseKeeperCards"
import AssigningstatusAssignHouseKeeperDetail from "./components/listers/AssigningstatusAssignHouseKeeperDetail"

import RoomRoomManager from "./components/listers/RoomRoomCards"
import RoomRoomDetail from "./components/listers/RoomRoomDetail"

import CleaningstatusCleaningStatusManager from "./components/listers/CleaningstatusCleaningStatusCards"
import CleaningstatusCleaningStatusDetail from "./components/listers/CleaningstatusCleaningStatusDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/reservations/accomodations',
                name: 'ReservationAccomodationManager',
                component: ReservationAccomodationManager
            },
            {
                path: '/reservations/accomodations/:id',
                name: 'ReservationAccomodationDetail',
                component: ReservationAccomodationDetail
            },

            {
                path: '/assigningstatuses/assignHouseKeepers',
                name: 'AssigningstatusAssignHouseKeeperManager',
                component: AssigningstatusAssignHouseKeeperManager
            },
            {
                path: '/assigningstatuses/assignHouseKeepers/:id',
                name: 'AssigningstatusAssignHouseKeeperDetail',
                component: AssigningstatusAssignHouseKeeperDetail
            },

            {
                path: '/rooms/rooms',
                name: 'RoomRoomManager',
                component: RoomRoomManager
            },
            {
                path: '/rooms/rooms/:id',
                name: 'RoomRoomDetail',
                component: RoomRoomDetail
            },

            {
                path: '/cleaningstatuses/cleaningStatuses',
                name: 'CleaningstatusCleaningStatusManager',
                component: CleaningstatusCleaningStatusManager
            },
            {
                path: '/cleaningstatuses/cleaningStatuses/:id',
                name: 'CleaningstatusCleaningStatusDetail',
                component: CleaningstatusCleaningStatusDetail
            },



    ]
})
