package com.pnfsoftware.jeb.core.units.code.asm.items;

import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import java.util.ArrayList;
import java.util.List;

public class StructureTypeFieldEventDetails {
   List createdFields = new ArrayList();
   List deletedFields = new ArrayList();
   List renamedFields = new ArrayList();

   public StructureTypeFieldEventDetails addCreatedField(IStructureTypeField var1) {
      if (var1 != null) {
         this.createdFields.add(var1);
      }

      return this;
   }

   public StructureTypeFieldEventDetails addDeletedField(IStructureTypeField var1) {
      if (var1 != null) {
         this.deletedFields.add(var1);
      }

      return this;
   }

   public StructureTypeFieldEventDetails addRenamedField(IStructureTypeField var1) {
      if (var1 != null) {
         this.renamedFields.add(var1);
      }

      return this;
   }

   public List getCreatedFields() {
      return this.renamedFields;
   }

   public List getDeletedFields() {
      return this.deletedFields;
   }

   public List getRenamedFields() {
      return this.renamedFields;
   }
}
