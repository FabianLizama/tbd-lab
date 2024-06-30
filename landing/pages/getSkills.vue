<script setup>
import { ref, reactive } from 'vue'
import { z } from 'zod'

const schema = z.object({
    rut: z.string().regex(/^\d{7,8}-\d{1}$/, 'RUT inválido')
})

const state = reactive({
    rut: ''
})

const skills = ref([])
const errorMessage = ref('')
const isLoading = ref(false)
const showErrorCircle = ref(false)
const showErrorMessage = ref(false)

async function handleSubmit() {
    errorMessage.value = ''
    skills.value = []
    isLoading.value = true
    showErrorCircle.value = false
    showErrorMessage.value = false
    
    const result = schema.safeParse(state)
    if (!result.success) {
        errorMessage.value = 'RUT inválido'
        await showError()
        return
    }
    
    try {
        const response = await $fetch(`http://localhost:8080/volunteers/skills/${state.rut}`, {
            method: 'GET'
        })
        skills.value = response
        if (skills.value.length === 0) {
            errorMessage.value = 'No se encontró el usuario, o no contiene Skills.'
            await showError()
        }
    } catch (error) {
        errorMessage.value = 'No se encontró el usuario, o no contiene Skills.'
        await showError()
    } finally {
        isLoading.value = false
    }
}

async function showError() {
    isLoading.value = false
    showErrorCircle.value = true
    await new Promise(resolve => setTimeout(resolve, 1000))
    showErrorMessage.value = true
}
</script>

<template>
    <div class="flex justify-center items-center min-h-[calc(100vh-72px)]">
        <UCard class="w-3/5 max-w-2xl">
            <h1 class="text-2xl font-bold text-primary text-center mb-4">Buscar Habilidades</h1>
            <UForm :state="state" class="space-y-4 text-center" @submit="handleSubmit">
                <UFormGroup label="RUT" name="rut">
                    <UInput v-model="state.rut" placeholder="20000002-3" />
                </UFormGroup>
                <UButton class="w-1/3 max-w-52 justify-center" type="submit" :loading="isLoading">
                    Buscar
                </UButton>
            </UForm>
            
            <div class="mt-4">
                <h2 class="text-xl font-bold text-primary text-center mb-2">Habilidades</h2>
                <transition name="fade-scale" mode="out-in">
                    <div v-if="showErrorCircle" key="error" class="flex items-center justify-center space-x-4">
                        <div class="error-circle">
                            <span class="text-white text-4xl font-bold">X</span>
                        </div>
                        <transition name="fade-slide">
                            <p v-if="showErrorMessage" class="text-red-500 font-semibold">{{ errorMessage }}</p>
                        </transition>
                    </div>
                    <transition-group v-else-if="skills.length > 0" name="list" tag="div">
                        <UCard v-for="skill in skills" :key="skill.code" class="mb-4 skill-card">
                            <h3 class="text-lg font-semibold">{{ skill.name }}</h3>
                            <p><strong>Código:</strong> {{ skill.code }}</p>
                            <p><strong>Items:</strong> {{ skill.items.join(', ') }}</p>
                            <p><strong>Descripción:</strong> {{ skill.description }}</p>
                        </UCard>
                    </transition-group>
                </transition>
            </div>
        </UCard>
    </div>
</template>

<style scoped>
.fade-scale-enter-active,
.fade-scale-leave-active {
    transition: all 0.5s ease;
}
.fade-scale-enter-from,
.fade-scale-leave-to {
    opacity: 0;
    transform: scale(0.5);
}

.list-enter-active,
.list-leave-active {
    transition: all 0.5s ease;
}
.list-enter-from,
.list-leave-to {
    opacity: 0;
    transform: translateY(30px);
}

.error-circle {
    width: 60px;
    height: 60px;
    border-radius: 50%;
    background-color: #f56565;
    display: flex;
    justify-content: center;
    align-items: center;
    transition: all 0.5s ease;
}

.skill-card {
    transition: all 0.5s ease;
}

.fade-slide-enter-active,
.fade-slide-leave-active {
    transition: all 0.5s ease;
}
.fade-slide-enter-from,
.fade-slide-leave-to {
    opacity: 0;
    transform: translateX(-20px);
}
</style>