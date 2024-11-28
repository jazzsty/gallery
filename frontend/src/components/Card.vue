<template>
  <div class="card shadow-sm">
    <span class="img" :style="{backgroundImage: `url(${item.imgPath})`}"></span>
    <div class="card-body">
      <p class="card-text">
        <!-- This is a wider card with supporting text below as a natural lead-in to additional
        content. This content is a little bit longer. -->
        {{ item.name }}
      </p>
      <div class="d-flex justify-content-between align-items-center">
        <button class="btn btn-primary">구입하기</button>
        <small class="text-body-secondary">{{ item.price }}</small>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "CardComponent",
  props: {
    // item: {
    //   type: [Object, String],
    //   Required: true
    // }
    item: Object
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
</style>
