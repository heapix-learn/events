<template>
  <div>
    <div class="generated-form row">
      <div v-for="(field, index) in formInputs" :key="index" class="col s12">
        <template v-if="['text', 'number'].indexOf(field.type) > -1">
          <div class="input-field col s5 offset-s3">
            <input :id="`form-field-${index}`" type="text" v-model="field.input">
            <label :for="`form-field-${index}`">{{field.label}}</label>
          </div>
        </template>
        <template v-else-if="field.type === 'select'">
          <div class="input-field col s5 offset-s3">
            <select id="main-select" v-model="field.input">
              <option v-for="(formOption, index) in field.options.split(', ')" :key="index" :value="formOption">{{formOption}}</option>
            </select>
            <label>{{field.label}}</label>
          </div>
        </template>
        <template v-else-if="field.type === 'image'">
            <form>
              <div class="file-field input-field">
                <div class="btn">
                  <span>File</span>
                  <input type="file">
                </div>
                <div class="file-path-wrapper">
                  <input :placeholder="field.label" class="file-path" type="text">
                </div>
              </div>
            </form>
        </template>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FormGeneratorView',
  props: ['formInputs'],
  mounted () {
    M.FormSelect.init(document.querySelectorAll('select'))
  }
}
</script>

<style>

</style>
