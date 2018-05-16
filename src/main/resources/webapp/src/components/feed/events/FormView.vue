<template>
        <div class="generated-form s12">
            <div class="row center">
            <span v-if="info">
                    <label class="col s12">Registration form info</label>
            <div v-for="(value, key) in info" :key="key" class="card-content black-text padding-user-preview">
                <div v-if="value.type === 'img'" class="">
                    <label class="col s12">{{key}}</label>
                    <img  class="small" :src="value.image" alt="logo">
                </div>
                <div v-else class="row">
                    <p class="col s12">{{key}} : <span>{{value}}</span></p>
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
    .padding-user-preview {
        padding-top: 2px !important;
        padding-bottom: 2px !important;
    }
</style>
