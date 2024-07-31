import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '@/auth/stores/auth';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('@/dashboard/views/HomeView.vue')
    },
    {
      path: '/myPage/product',
      name: 'product',
      component: () => import('@/dashboard/views/ProductView.vue')
    },
    {
      path: '/myPage/pwd',
      name: 'pwd',
      component: () => import('@/dashboard/views/PwdChange.vue')
    },
    {
      path: '/user/login',
      name: 'login',
      component: () => import('@/auth/views/LoginView.vue'),
    },
    {
      path: '/user/join',
      name: 'join',
      component: () => import('@/auth/views/SignupView.vue'),
    },
    {
      path: '/user/find',
      name: 'find',
      component: () => import('@/auth/views/FindPwdView.vue'),
    }
  ]
});

router.beforeEach((from, to, next) => {
  const token = sessionStorage.getItem("teongbinToken");

  console.log(from);
  const allowPages = ['login', 'join', 'find'];
  
  if(token) {
    const authStore = useAuthStore();
    authStore.loginState = true;
    if(allowPages.includes(from.name)) {
      return next({name: 'home'});
    }
    return next();
  } else {
    if(allowPages.includes(from.name)) {
      return next();
    }
    next({name: 'login'});
  }
});

export default router;