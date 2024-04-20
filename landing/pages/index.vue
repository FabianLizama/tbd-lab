<script setup>
const user = useState('user');
const router = useRouter();
const tokenCookie = useCookie('token');
if (!tokenCookie.value) {
    router.push('/login');
}
if (user.value === 'user') {
    if (user.value.type_user_id === 0) {
        router.push('/coordination');
    } else if (user.value.type_user_id === 1) {
        router.push('/volunteer');
    }
}

</script>

<template>
    <div>

    </div>
</template>