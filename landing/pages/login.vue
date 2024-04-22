<script setup>
import { z } from 'zod';
const colorMode = useColorMode();
const user = userStore();
const tokenCookie = useCookie('token');

if (tokenCookie.value) {
    if (user.type_user_id === 0) {
        router.push('/coordination');
    } else if (user.type_user_id === 1) {
        router.push('/volunteer');
    }
}

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
        try {
            const userResponse = await $fetch(`http://localhost:8080/api/user/email/${state.email}`, {
                method: 'GET',
            });
            user.setUser(userResponse);
        } catch (error) {
            const toast = useToast();
            toast.add({ title: 'Error en el servidor', color: 'red' });
        }
        const router = useRouter();
        if (user.type_user_id === 0) {
            router.push('/coordination');
        } else if (user.type_user_id === 1) {
            router.push('/volunteer');
        }
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
    <div class="flex justify-center items-center content-below-appbar">
        <UCard class="w-3/5 max-w-2xl">
            <h1 class="text-primary font-bold text-center text-inherit text-3xl py-5">Inicio de Sesión</h1>
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
                <UFormGroup>
                    <ULink
                    to="/register"
                    class="text-primary text-end underline text-sm"
                    >
                        No tengo una cuenta
                    </ULink>
                </UFormGroup>
            </UForm>
        </UCard>
    </div>
</template>

<style>
.content-below-appbar {
    height: calc(100vh - 72px)
}
</style>