<script setup>
const user = userStore();
const tokenCookie = useCookie('token');
const isOpen = ref(false);

const em_columns = [{
    key: 'emergencyname',
    label: 'Emergencia'
}, {
    key:'institutionname',
    label: 'Institución'
}, {
    key: 'coordinatorname',
    label: 'Coordinador'
}, {
    key: 'actions',
    label: 'Tareas',
}
]

const ts_columns = [{
    key: 'task_id',
    label: 'ID'
}, {
    key: 'task_name',
    label: 'Tarea'
  }, {
    key: 'task_state',
    label: 'Estado'
  }, {
    key: 'actions',
    label: 'Acciones'
  }]

const emergencies = await $fetch('http://localhost:8080/api/emergency/actives/view', {
    method: 'GET',
    params: {
        token: tokenCookie.value
    }
});

const em_page = ref(1)
const em_pageCount = 10

const em_rows = computed(() => {
  return emergencies.slice((em_page.value - 1) * em_pageCount, (em_page.value) * em_pageCount)
})

const tasks = ref([])

const task_states = ['Created        ', 'Assigned       ', 'In Progress    ', 'Completed      '];

const taskStatesMap = reactive({});

async function openTasks(emergency_id) {
    tasks.value = await $fetch(`http://localhost:8080/api/task/view/emergency/${emergency_id}`, {
        method: 'GET',
        params: {
            token: tokenCookie.value
        }
    });
    tasks.value.forEach(task => {
        taskStatesMap[task.task_id] = task.task_state;
    });
    isOpen.value = true;
}

async function onRelease(task_id, task_state_id){
  await $fetch('http://localhost:8080/api/task_state', {
    method: 'PUT',
    body: JSON.stringify({
      task_state_id: task_state_id,
      state: taskStatesMap[task_id]
    }),
  })
  const toast = useToast();
  toast.add({ title: 'Estado actualizado', color: 'primary' });

  reloadNuxtApp();
}

async function assignVolunteer(task_id) {
  const volunteer_id = await $fetch(`http://localhost:8080/api/volunteers/getVolunteerIdByUserId/${user.user_id}`, {
    method: 'GET',
  });
  await $fetch('http://localhost:8080/api/ranking', {
    method: 'POST',
    body: JSON.stringify({
      task_id: task_id,
      volunteer_id: volunteer_id,
    }),
  });
  const toast = useToast();
  toast.add({ title: 'Voluntario asignado', color: 'primary' });
}

</script>

<template>
    <div class="flex justify-center items-center content-below-appbar">
        <UCard>
            <h1 class="text-primary font-bold text-center text-inherit text-2xl pb-5 pt-2">Emergencias</h1>
            <UTable :rows="em_rows" :columns="em_columns">
              <template #actions-data="{ row }">
                <UButton color="gray" size="lg" variant="ghost" icon="i-heroicons-clipboard-document-list-solid" @click="openTasks(row.emergencyid)"/>
              </template>
            </UTable>
            <div class="flex justify-end px-3 py-3.5 border-t border-gray-200 dark:border-gray-700">
            <UPagination v-model="em_page" :page-count="em_pageCount" :total="emergencies.length" />
            </div>
        </UCard>
        <UModal v-model="isOpen" fullscreen>
      <UCard class="p-10 w-full h-full">
        <template #header>
              <div class="flex items-center justify-between">
                <h1 class="text-primary text-center text-inherit text-2xl pb-5 pt-2">Tareas</h1>
                <UButton color="gray" variant="ghost" icon="i-heroicons-x-mark-20-solid" class="-my-1" @click="isOpen = false" />
              </div>
            </template>
        <UTable :rows="tasks" :columns="ts_columns">
          <template #task_state-data="{ row }">
            <USelect v-model="taskStatesMap[row.task_id]" size="md" :options="task_states" @change="onRelease(row.task_id, row.task_state_id)"/>
          </template>
          <template #actions-data="{ row }">
            <div class="flex text-start">
              <UButton class="mr-3" size="md" label="Asignar" icon="i-heroicons-user-plus-16-solid" @click="assignVolunteer(row.task_id)"/>
            </div>
          </template>
        </UTable>
      </UCard>
    </UModal>
    </div>
</template>

<style>
.content-below-appbar {
    height: calc(100vh - 72px)
}
</style>