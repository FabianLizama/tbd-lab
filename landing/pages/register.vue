<script setup>
import { z } from 'zod';
import { useRouter } from 'vue-router';

const user = userStore();
const router = useRouter();

const schema = z.object({
    name: z.string().min(1, 'El nombre es requerido'),
    lastName: z.string().min(1, 'El apellido es requerido'),
    email: z.string().email('Correo inválido'),
    password: z.string().min(8, 'Contraseña debe tener al menos 8 caracteres'),
    confirmPassword: z.string().min(8, 'Contraseña debe tener al menos 8 caracteres'),
    phone: z.string().min(12, 'Número de teléfono inválido'),
}).refine(data => data.password === data.confirmPassword, {
    message: 'Las contraseñas no coinciden',
    path: ['confirmPassword'],
});

const state = reactive({
    name: undefined,
    lastName: undefined,
    email: undefined,
    password: undefined,
    confirmPassword: undefined,
    phone: undefined,
});

async function handleSubmit (event) {
    const result = schema.safeParse(state);
    if (!result.success) {
        const toast = useToast();
        toast.add({ title: 'Error en los datos ingresados', color: 'red' });
        return;
    }

    const userObj = {
        name: `${state.name} ${state.lastName}`,
        email: state.email,
        password: state.password,
        phone: state.phone
    };

    try {
        const response = await $fetch('http://localhost:8080/createUser', {
            method: 'POST',
            body: JSON.stringify(userObj),
        });
        
        user.setUser(response);

        router.push('/getSkills');
    } catch (error) {
        if (error.response) {
            if (error.response.status === 400) {
                const toast = useToast();
                toast.add({ title: 'Error en los datos ingresados', color: 'red' });
            } else if (error.response.status === 409) {
                const toast = useToast();
                toast.add({ title: 'El correo ya está registrado', color: 'red' });
            } else {
                const toast = useToast();
                toast.add({ title: 'Error en el servidor', color: 'red' });
            }
        } else {
            const toast = useToast();
            toast.add({ title: 'Error de conexión', color: 'red' });
        }
    }
}
</script>

<template>
    <div class="flex justify-center items-center h-screen">
        <UCard class="w-3/5 max-w-2xl">
            <div>
                <h1 class="text-primary font-bold text-center text-inherit text-3xl pb-5 pt-2">Registro de Usuario</h1>
            </div>
            <UForm :schema="schema" :state="state" class="space-y-4 text-center" @submit="handleSubmit">
                <UFormGroup label="Nombres" name="name">
                    <UInput v-model="state.name" />
                </UFormGroup>

                <UFormGroup label="Apellidos" name="lastName">
                    <UInput v-model="state.lastName" />
                </UFormGroup>

                <UFormGroup label="Email" name="email">
                    <UInput placeholder="you@example.com" v-model="state.email" />
                </UFormGroup>
                
                <UFormGroup label="Contraseña" name="password">
                    <UInput v-model="state.password" type="password" />
                </UFormGroup>
                
                <UFormGroup label="Confirmar Contraseña" name="confirmPassword">
                    <UInput v-model="state.confirmPassword" type="password" />
                </UFormGroup>
                
                <UFormGroup label="Celular" name="phone" type="tel">
                    <UInput placeholder="+56912345678" v-model="state.phone" />
                </UFormGroup>

                <UButton class="w-1/3 max-w-52 justify-center" type="submit">
                    Registrarse
                </UButton>
                <UFormGroup>
                    <ULink
                    to="/login"
                    class="text-primary text-end underline text-sm"
                    >
                        Ya tengo una cuenta
                    </ULink>
                </UFormGroup>
            </UForm>
        </UCard>
    </div>
</template>