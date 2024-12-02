<template>
  <div class="orders">
    <div class="container">
      <table class="table table-bordered">
        <thead>
          <tr>
            <th>번호</th>
            <th>주문자명</th>
            <th>주소</th>
            <th>결제수단</th>
            <th>구입항목</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(order, i) in state.orders" :key="i">
            <td>{{ state.orders.length - i }}</td>
            <td>{{ order.name }}</td>
            <td>{{ order.address }}</td>
            <td>{{ order.payment }}</td>
            <td>
              <div v-for="(item, i) in order.items" :key="i">{{ item.name }}</div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script>
import { reactive } from "vue";
import lib from "@/scripts/lib";
import axios from "axios";

export default {
  name: "OrdersComponent",
  setup() {
    const state = reactive({
      orders: []
    });

    axios.get("/api/orders").then((res) => {
      // console.log("res: ", res);
      state.orders = [];

      for (let order of res.data) {
        if (order.items) {
          // console.log('order.items: ', order.items);
          order.items = JSON.parse(order.items);
        }
        // console.log('order: ', order);
        state.orders.push(order);
      }
    });

    return { state, lib };
  },
};
</script>
<style scoped>
.total-price {
  font-weight: bold;
}

table {
  margin-top: 30px;
}
</style>
