<script setup>
import { z } from 'zod';
const colorMode = useColorMode();

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
  email: z.string().email('Correo inválido'),
  password: z.string().min(8, 'Contraseña debe tener al menos 8 carácteres'),
})

const state = reactive({
    userType: 0,
    name: undefined,
    email: undefined,
    password: undefined,
    confirmPassword: undefined,
    phone: undefined,
});

async function handleSubmit (event) {
    const body = JSON.stringify({
                type_user_id: state.userType,
                name: state.name,
                email: state.email,
                password: state.password,
                phone: state.phone
            });
    try {
        const response = await $fetch('http://localhost:8080/api/user/register', {
            method: 'POST',
            body: body,
        });
        console.log(response);
    } catch (error) {
        console.error(error);
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
                    <h1 class="text-primary text-center text-inherit text-3xl pb-5 pt-2">Registro de Usuario</h1>
            </div>
            <UForm :schema="schema" :state="state" class="space-y-4" @submit="handleSubmit">
                <h6>Voluntario o Coordinador</h6>
                <UFormGroup label="Voluntario?" name="userType">
                    <UInput v-model="state.userType" />
                </UFormGroup>
                <UFormGroup label="Name" name="name">
                    <UInput v-model="state.name" />
                </UFormGroup>

                <UFormGroup label="Email" name="email">
                    <UInput v-model="state.email" />
                </UFormGroup>
                
                <UFormGroup label="Password" name="password">
                    <UInput v-model="state.password" type="password" />
                </UFormGroup>
                
                <UFormGroup label="Confirm Password" name="confirmPassword">
                    <UInput v-model="state.confirmPassword" type="password" />
                </UFormGroup>
                
                <UFormGroup label="Phone" name="phone">
                    <UInput v-model="state.phone" />
                </UFormGroup>

                <UButton type="submit">
                    Submit
                </UButton>
            </UForm>
        </UCard>
        
    </div>
</template>