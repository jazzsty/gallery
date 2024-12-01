<template lang="">
    <div class="cart">
        <div class="container">
            <ul>
                <li v-for="(item, idx) in state.items" :key="idx">
                    <img :src="item.imgPath" alt="">
                    <span class="name">{{ item.name }}</span>
                    <!-- <span class="price">{{ item.price }}</span> -->
                    <span class="price">{{ lib.getNumberFormatted(item.price - (item.price * item.discountPer / 100)) }}원</span>
                    <i class="fa fa-trash" @click="remove(item.id)"></i>
                </li>
            </ul>
            <router-link to="/order" class="btn btn-primary">구입하기</router-link>
        </div>
    </div>
</template>
<script>
import axios from 'axios';
import { reactive } from 'vue';
import lib from '@/scripts/lib';

export default {
    name: "CartComponent",
    setup() {
        const state = reactive({
            items: []
        })

        const load = () => {
            axios.get("/api/cart/items").then((res) => {
                console.log("res: ", res.data);
                state.items = res.data;
            })
        }

        const remove = (itemId) => {
            console.log('itemId: ', itemId);
            axios.delete(`/api/cart/items/${itemId}`).then((res) => {
                console.log('res: ', res);
                load();
            })
        }

        const order = (itemId) => {
            console.log('itemId: ', itemId);
            // axios.
        }

        load();

        return {state, lib, remove, order}
    }
}
</script>
<style scoped>
    .cart ul {
        list-style: none;
        margin: 0;
        padding: 0;
    }

    .cart ul li {
        border: 1px solid #eee;
        margin-top: 25px;
        margin-bottom: 25px;
    }

    .cart ul li img {
        width: 150px;
        height: 150px;
    }

    .cart ul li .name {
        margin-left: 25px;
    }

    .cart ul li .price {
        margin-left: 25px;
    }

    .cart ul li i {
        float: right;
        font-size: 20px;
        margin-top: 65px;
        margin-right: 50px;
    }

    .cart .btn {
        display: block;
        margin: auto;
        font-size: 20px;
        padding: 20px 20px;
    }
</style>