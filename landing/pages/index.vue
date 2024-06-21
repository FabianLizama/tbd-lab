<script setup>
const user = userStore();
const router = useRouter();
const tokenCookie = useCookie('token');
if (!tokenCookie.value) {
    router.push('/getSkills');
}
if (user.type_user_id === 0) {
    router.push('/coordination');
} else if (user.type_user_id === 1) {
    router.push('/volunteer');
}

</script>

<template>
    <div>

    </div>
</template>