<script setup>

const router = useRouter();
const tokenCookie = useCookie('token');
if (!tokenCookie.value) {
    router.push('/login');
}

</script>

<template>
    <div>

    </div>
</template>