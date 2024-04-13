<script setup>
import { z } from 'zod';
const colorMode = useColorMode();
const userStore = useUserStore();


const isDark = computed({
  get () {
    return colorMode.value === 'dark'
  },
  set () {
    colorMode.preference = colorMode.value === 'dark' ? 'light' : 'dark'
  }
})

const config = useRuntimeConfig();

const schema = z.object({
    email: z.string().email('Correo inválido')

});

const state = reactive({
    email: undefined,
    password: undefined,
});


async function handleSubmit (event) {
    const result = schema.safeParse(state);
    if (!result.success) {
        const toast = useToast();
        toast.add({ title: 'Error en los datos ingresados', color: 'red' });
        return;
    }
    
    const body = JSON.stringify({
        email: state.email,
        password: state.password,
            });
    try {
        const response = await $fetch('http://localhost:8080/api/user/login', {
            method: 'POST',
            body: body,
        });
        const tokenCookie = useCookie('token');
        tokenCookie.value = response;
        const router = useRouter();
        router.push('/');
    } catch (error) {
        if (error.response){
            if (error.response.status === 400) {
                const toast = useToast();
                toast.add({ title: 'Error en los datos ingresados', color: 'red' });
            } else if (error.response.status === 401) {
                const toast = useToast();
                toast.add({ title: 'Credenciales incorrectas', color: 'red' });
            } else {
                const toast = useToast();
                toast.add({ title: 'Error en el servidor', color: 'red' });
            }
        }
    }
}

</script>

<template>
    <div class="flex justify-center items-center h-screen">
        <UCard class="w-3/5">
            <div>
                <div class="text-end">
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
                </div>
                    <h1 class="text-primary text-center text-inherit text-3xl pb-5 pt-2">Inicio de Sesión</h1>
            </div>
            <UForm :schema="schema" :state="state" class="space-y-4 text-center" @submit="handleSubmit">
                <UFormGroup label="Email" name="email">
                    <UInput placeholder="you@example.com" v-model="state.email" />
                </UFormGroup>
                
                <UFormGroup label="Contraseña" name="password">
                    <UInput v-model="state.password" type="password" />
                </UFormGroup>
                <UButton class="w-1/3 max-w-52 justify-center" type="submit">
                    Submit
                </UButton>
            </UForm>
        </UCard>
    </div>
</template>