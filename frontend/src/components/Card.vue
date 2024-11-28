<template>
  <div class="card shadow-sm">
    <span class="img" :style="{ backgroundImage: `url(${item.imgPath})` }"></span>
    <div class="card-body">
      <p class="card-text">
        <span>
          {{ item.name }}&nbsp;
        </span>
        <span class="discount badge bg-danger">
          {{ item.discountPer }}%
        </span>
      </p>
      <div class="d-flex justify-content-between align-items-center">
        <button class="btn btn-primary">구입하기</button>
        <!-- <small class="text-body-secondary">{{ lib.getNumberFormatted(item.price) }}</small> -->
        <small class="price text-muted">
          {{ lib.getNumberFormatted(item.price) }}
        </small>
        
        <small class="discount text-danger">
          {{ lib.getNumberFormatted(item.price - (item.price * item.discountPer / 100)) }}원
        </small>
      </div>
    </div>
  </div>
</template>
<script>
import lib from "@/scripts/lib";

export default {
  name: "CardComponent",
  props: {
    item: Object
  },
  setup() {
    return { lib }
  }
};
</script>
<style>
.card .img {
  display: inline-block;
  width: 100%;
  height: 250px;
  background-size: cover;
  background-position: center;
}

.card .card-body .price {
  text-decoration: line-through;
}
</style>
