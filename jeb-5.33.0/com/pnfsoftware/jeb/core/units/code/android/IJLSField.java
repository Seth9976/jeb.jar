package com.pnfsoftware.jeb.core.units.code.android;

public interface IJLSField {
   String getName();

   String getDescriptor();

   int getAccessFlags();

   default String getSignature() {
      return this.getName() + ":" + this.getDescriptor();
   }
}
