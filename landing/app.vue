<script setup>
const user = userStore()
</script>

<template>
  <div class="h-screen">
    <appbar />
    <NuxtPage />
    <UNotifications />
  </div>
</template>
