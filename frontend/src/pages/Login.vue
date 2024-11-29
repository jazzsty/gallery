<template>
    <div class="form-signin w-100 m-auto">
        <!-- <form> -->
        <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

        <div class="form-floating">
            <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com"
                v-model="state.form.email">
            <label for="floatingInput">Email address</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="floatingPassword" placeholder="Password"
                v-model="state.form.password">
            <label for="floatingPassword">Password</label>
        </div>

        <div class="form-check text-start my-3">
            <input class="form-check-input" type="checkbox" value="remember-me" id="flexCheckDefault">
            <label class="form-check-label" for="flexCheckDefault">
                Remember me
            </label>
        </div>
        <button class="btn btn-primary w-100 py-2" @click="submit()">Sign in</button>
        <p class="mt-5 mb-3 text-body-secondary">&copy; 2017–2024</p>
        <!-- </form> -->
    </div>
</template>
<script>
import router from '@/scripts/router';
import store from '@/scripts/store';
import axios from 'axios';
import { reactive } from 'vue';

export default {
    name: 'LoginComponent',
    setup() {
        const state = reactive({
            form: {
                email: "",
                password: ""
            }
        })

        const submit = () => {
            axios.post("/api/member/login", state.form).then((res) => {
                console.log(res);
                store.commit('setMember', res.data);
                sessionStorage.setItem("id", res.data);
                router.push({path: "/"});
                alert("로그인하였습니다.");
            }).catch(() => {
                alert("로그인 정보가 존재하지 않습니다.");
            })
        }
        return { state, submit }
    }
}
</script>
<style scoped>
.form-signin {
    max-width: 330px;
    padding: 1rem;
}

.form-signin .form-floating:focus-within {
    z-index: 2;
}

.form-signin input[type="email"] {
    margin-bottom: -1px;
    border-bottom-right-radius: 0;
    border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
    margin-bottom: 10px;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
}
</style>