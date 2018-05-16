<template>
    <div class="col s12">
        <div class="generated-form row center">
            <span v-if="info">
                    <label style="text-align: center">Registration form info</label>
            <div v-for="(value, key) in info" :key="key" class="card-content black-text">
                <div v-if="value.type === 'img'" class="row">
                    <label class="col s12">{{key}}</label>
                    <img  class="small" :src="value.image" alt="logo">
                </div>
                <div v-else class="row">
                    <p class="">{{key}} : <span>{{value}}</span></p>
                </div>
            </div>
            </span>
        </div>
    </div>
</template>


<script>
    export default {
        name: 'FormView',
        props: ['info'],
        mounted() {
        },
    }
</script>

<style>
    .small {
        width: 200px;
        height: 200px;
        border-radius: 100px;
    }
</style>
