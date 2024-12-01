<template>
    <div class="order">
        <div class="container">
            <main>
                <div class="py-5 text-center">
                    <h2>주문하기</h2>
                    <p class="lead">Below is an example form built entirely with Bootstrap’s form controls. Each
                        required form group has a validation state that can be triggered by attempting to submit the
                        form without completing it.</p>
                </div>
                <div class="row g-5">
                    <div class="col-md-5 col-lg-4 order-md-last">
                        <h4 class="d-flex justify-content-between align-items-center mb-3"><span
                                class="text-primary">Your cart</span><span
                                class="badge bg-primary rounded-pill">3</span></h4>
                        <ul class="list-group mb-3">
                            <li class="list-group-item d-flex justify-content-between lh-sm">
                                <div>
                                    <h6 class="my-0">Product name</h6><small class="text-muted">Brief
                                        description</small>
                                </div><span class="text-muted">$12</span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between lh-sm">
                                <div>
                                    <h6 class="my-0">Second product</h6><small class="text-muted">Brief
                                        description</small>
                                </div><span class="text-muted">$8</span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between lh-sm">
                                <div>
                                    <h6 class="my-0">Third item</h6><small class="text-muted">Brief description</small>
                                </div><span class="text-muted">$5</span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between bg-light">
                                <div class="text-success">
                                    <h6 class="my-0">Promo code</h6><small>EXAMPLECODE</small>
                                </div><span class="text-success">−$5</span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between"><span>Total
                                    (USD)</span><strong>$20</strong></li>
                        </ul>
                        <form class="card p-2">
                            <div class="input-group"><input type="text" class="form-control" placeholder="Promo code"
                                    spellcheck="false" data-ms-editor="true"><button type="submit"
                                    class="btn btn-secondary">Redeem</button></div>
                        </form>
                    </div>
                    <div class="col-md-7 col-lg-8">
                        <h4 class="mb-3">주문자 정보</h4>
                        <form class="needs-validation" novalidate="">
                            <div class="row g-3">
                                <div class="col-12"><label for="username" class="form-label">이름</label>
                                    <input type="text" class="form-control" id="username" placeholder="Username"
                                        required="">
                                </div>
                                <div class="col-12"><label for="address" class="form-label">주소</label><input type="text"
                                        class="form-control" id="address" placeholder="1234 Main St" required=""
                                        spellcheck="false" data-ms-editor="true">
                                </div>
                            </div>
                            <hr class="my-4">
                            <h4 class="mb-3">결제 수단</h4>
                            <div class="my-3">
                                <div class="form-check"><input id="credit" name="paymentMethod" type="radio"
                                        class="form-check-input" checked="" required=""><label class="form-check-label"
                                        for="credit">신용카드</label></div>
                                <div class="form-check"><input id="debit" name="paymentMethod" type="radio"
                                        class="form-check-input" required=""><label class="form-check-label"
                                        for="debit">무통장입금</label></div>
                            </div>
                            <div class="col-12">
                                <label for="cc-name" class="form-label">카드 번호</label><input type="text"
                                    class="form-control" id="cc-name" placeholder="" required="">
                            </div>
                            <hr class="my-4"><button class="w-100 btn btn-primary btn-lg" type="submit">Continue to
                                checkout</button>
                        </form>
                    </div>
                </div>
            </main>
        </div>
    </div>
</template>
<script>
import axios from 'axios';
import { reactive } from 'vue';
import lib from '@/scripts/lib';

export default {
    name: "OrderComponent",
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

        load();

        return { state, lib }
    }
}
</script>
<style scoped></style>