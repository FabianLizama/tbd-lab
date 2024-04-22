<script setup>
// lógica tabla de emergencias
const tokenCookie = useCookie('token');

const em_columns = [{
    key: 'emergency_id',
    label: 'ID',
}, {
    key: 'name',
    label: 'Emergencia',
}, {
    key: 'quantity_volunteers',
    label: 'Voluntarios Inscritos',
}
]

const emergencies = await $fetch('http://localhost:8080/api/emergency/view', {
  method: 'GET',
  params: {
    token: tokenCookie.value
  }
})

/*const emergencies = [
  { emergency_id: 1, name: 'Incendio', quantity_volunteers: 10 },
  { emergency_id: 2, name: 'Inundación', quantity_volunteers: 5 },
  { emergency_id: 3, name: 'Terremoto', quantity_volunteers: 15 },
  { emergency_id: 4, name: 'Deslizamiento', quantity_volunteers: 3 },
  { emergency_id: 5, name: 'Tsunami', quantity_volunteers: 7 },
  { emergency_id: 6, name: 'Erupción Volcánica', quantity_volunteers: 2 },
  { emergency_id: 7, name: 'Tornado', quantity_volunteers: 1 },
  { emergency_id: 8, name: 'Tormenta Eléctrica', quantity_volunteers: 4 },
  { emergency_id: 9, name: 'Ciclón', quantity_volunteers: 6 },
  { emergency_id: 10, name: 'Tifón', quantity_volunteers: 8 },
  { emergency_id: 11, name: 'Huracán', quantity_volunteers: 9 },
  { emergency_id: 12, name: 'Tormenta de Arena', quantity_volunteers: 11 },
  { emergency_id: 13, name: 'Tormenta de Nieve', quantity_volunteers: 12 },
  { emergency_id: 14, name: 'Tormenta de Granizo', quantity_volunteers: 13 },
  { emergency_id: 15, name: 'Tormenta de Lluvia', quantity_volunteers: 14 },
  { emergency_id: 16, name: 'Tormenta de Granizo', quantity_volunteers: 15 },
  { emergency_id: 17, name: 'Tormenta de Lluvia', quantity_volunteers: 16 },
  { emergency_id: 18, name: 'Tormenta de Granizo', quantity_volunteers: 17 },
  { emergency_id: 19, name: 'Tormenta de Lluvia', quantity_volunteers: 18 },
  { emergency_id: 20, name: 'Tormenta de Granizo', quantity_volunteers: 19 }
]
*/
const em_page = ref(1)
const em_pageCount = 10

const em_rows = computed(() => {
  return emergencies.slice((em_page.value - 1) * em_pageCount, (em_page.value) * em_pageCount)
})

// lógica tabla de tareas

const ta_columns = [{
    key: 'task_id',
    label: 'ID',
}, {
    key: 'task_name',
    label: 'Tarea',
}, {
    key: 'volunteer_quantity',
    label: 'Voluntarios Inscritos',
}
]

const tasks = await $fetch('http://localhost:8080/api/task/view', {
  method: 'GET',
  params: {
    token: tokenCookie.value
  }
})

const ta_page = ref(1)
const ta_pageCount = 10

const ta_rows = computed(() => {
  return tasks.slice((ta_page.value - 1) * ta_pageCount, (ta_page.value) * ta_pageCount)
})
</script>

<template>
  <div class="flex pt-14 items-center justify-around">
    <UCard>
        <h1 class="text-primary text-center text-inherit text-2xl pb-5 pt-2">Emergencias</h1>
        <UTable :rows="em_rows" :columns="em_columns"/>
        <div class="flex justify-end px-3 py-3.5 border-t border-gray-200 dark:border-gray-700">
          <UPagination v-model="em_page" :page-count="em_pageCount" :total="emergencies.length" />
        </div>
    </UCard>
    <UCard>
        <h1 class="text-primary text-center text-inherit text-2xl pb-5 pt-2">Tareas</h1>
        <UTable :rows="ta_rows" :columns="ta_columns"/>
        <div class="flex justify-end px-3 py-3.5 border-t border-gray-200 dark:border-gray-700">
          <UPagination v-model="ta_page" :page-count="ta_pageCount" :total="tasks.length" />
        </div>
    </UCard>
  </div>
</template>