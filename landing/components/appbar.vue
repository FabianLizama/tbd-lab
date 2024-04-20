<script setup>
const colorMode = useColorMode();

const isDark = computed({
  get () {
    return colorMode.value === 'dark'
  },
  set () {
    colorMode.preference = colorMode.value === 'dark' ? 'light' : 'dark'
  }
})

const tokenCookie = useCookie('token');
const userStore = useState('user')
const router = useRouter();

function logout() {
    tokenCookie.value = undefined;
    userStore.value = {
        user_id: -1,
        type_user_id: -1,
        name: '',
        email: '',
        phone: ''
    }
    router.push('/login');
}

</script>

<template>
    <div class="py-3.5 px-6 shadow md:flex justify-between items-center ">
        <div class="flex items-center justify-between">
            <ClientOnly>
                <UButton
                :icon="isDark ? 'i-heroicons-moon-20-solid' : 'i-heroicons-sun-20-solid'"
                color="gray"
                variant="ghost"
                aria-label="Theme"
                @click="isDark = !isDark"
                />
                <template #fallback>
                    <div class="w-8 h-8" />
                </template>
            </ClientOnly>
            <h1 class="text-xl font-bold pl-5">Sistema de Voluntariado</h1>
        </div>
        <div class="flex items-center justify-between">
            <UButton v-if="tokenCookie !== undefined" icon="i-heroicons-user-circle" class="rounded-full" size="xl" variant="ghost" label="Perfil" trailing to="/profile"/>
            <UButton v-if="tokenCookie !== undefined" icon="i-heroicons-arrow-left-on-rectangle" class="rounded-full" size="xl" variant="ghost" trailing @click="logout"/>
        </div>
    </div>
</template>