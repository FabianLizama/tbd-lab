<script setup>
const userStore = useState('user', () => {
  return {
    user_id: -1,
    type_user_id: -1,
    name: '',
    email: '',
    phone: ''
  }
})

</script>

<template>
  <div class="h-screen">
    <appbar />
    <NuxtPage />
    <UNotifications />
  </div>
</template>
