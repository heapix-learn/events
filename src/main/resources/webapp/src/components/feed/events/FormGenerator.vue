<template>
  <div>
    <div class="form-generator row">
      <div class="input-field col s5 offset-s3">
        <select v-model="typeToAdd" id="main-select">
          <option value="text">Text</option>
          <option value="number">Numeric</option>
          <option value="select">Select</option>
          <option value="image">Image</option>
        </select>
        <label>Input Type</label>
      </div>
      <div class="add-button col s2 valign-center">
        <a @click="addField" class="waves-effect green waves-light btn"><i class="material-icons left">add</i>Add field</a>
      </div>
    </div>
    <div class="generated-fields row">
      <div v-for="(field, index) in formInputs" :key="index" class="col s12">
          <div class="input-field col s5 offset-s3">
            <input :id="`form-field-${index}`" type="text" v-model="formInputs[index].label">
            <label :for="`form-field-${index}`">Label name</label>
          </div>
          <div class="input-delete col s2 valign-wrapper">
            <i @click="removeField(index)" class="material-icons red-text delete-input">clear</i>
          </div>
          <div v-if="field.type === 'select'" class="input-field col s5 offset-s3">
            <input placeholder="option1, option2, option3..." :id="`form-field-${index}`" type="text" v-model="formInputs[index].options">
            <label class="active" :for="`form-field-${index}`">Options separated by comma</label>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FormGenerator',
  props: ['formInputs'],
  data () {
    return {
      typeToAdd: 'text',
      // formInputs: [
        // {type: 'text', label: ''},
        // {type: 'number', label: ''},
        // {type: 'select', label: ''},
        // {type: 'image', label: ''}
      // ],
    }
  },
  methods: {
    addField() {
      this.formInputs.push({type: this.typeToAdd, label: ''})
    },
    removeField(i) {
      this.formInputs.splice(i, 1)
    }
  },
  mounted () {
    M.FormSelect.init(document.querySelector('#main-select'))
  }
}
</script>

<style>
.add-button {
  margin: 1rem 0;
}
.form-generator {
  padding-top: 15px;
}
.input-delete {
  margin: 1rem 0;
  height: 50px;
}
.input-delete i:hover {
  cursor: pointer;
}
</style>
