package com.asmap.model

class Composite extends Component {

    boolean isRoot = false

    List<Component> children = []

    void addChild(Component component) {
        children.add component
        component.parent = this
    }

    boolean isOneToMany() {
         if ( !isRoot ) {
             return parent.entityGrailsClass.getPropertyByName(fieldNameToLoad).isOneToMany()
         }
         return false
     }

     boolean isManyToMany() {
         if ( !isRoot ) {
             return parent.entityGrailsClass.getPropertyByName(fieldNameToLoad).isManyToMany()
         }
         return false
     }


     boolean isManyToOne() {
         if ( !isRoot ) {
             return parent.entityGrailsClass.getPropertyByName(fieldNameToLoad).isManyToOne()
         }
         return false
     }


     boolean isOneToOne() {
         if ( !isRoot ) {
             return parent.entityGrailsClass.getPropertyByName(fieldNameToLoad).isOneToOne()
         }
         return false
     }


     boolean isEmbedded() {
         if ( !isRoot ) {
             return parent.entityGrailsClass.getPropertyByName(fieldNameToLoad).isEmbedded()
         }
         return false
     }

}
