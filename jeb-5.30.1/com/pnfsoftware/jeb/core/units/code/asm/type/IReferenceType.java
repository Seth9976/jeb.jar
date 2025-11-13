package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IReferenceType extends INativeType {
   INativeType getMainType();

   int getReferenceCount();

   default INativeType getReferencedType() {
      return this.getPointedType();
   }

   INativeType getPointedType();
}
