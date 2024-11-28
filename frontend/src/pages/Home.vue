<template>
  <div class="home">
    <div class="album py-5 bg-body-tertiary">
      <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
          <div v-for="(item, i) in state.items" :key="i" class="col">
            <!-- {{ item }} -->
            <Card :item="item" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Card from '@/components/Card.vue';
import axios from 'axios';
import { reactive } from 'vue';

export default {
  name: 'HomeComponent',
  components: {
    Card
  },
  setup() {
    const state = reactive({
      items: []
    })
    axios.get('http://localhost:8080/api/items').then(({ data }) => {
      console.log('data: ', data);
      // state.items = res.data;
      state.items = data;
    });

    return { state }
  },
};
</script>
<style></style>