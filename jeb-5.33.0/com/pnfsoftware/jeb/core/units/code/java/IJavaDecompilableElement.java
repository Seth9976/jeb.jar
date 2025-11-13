package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaDecompilableElement extends IJavaElement {
   IJavaGlobalContext getGlobalContext();

   String getName();

   String getSignature();

   boolean isExternal();

   default void markBuilt() {
      this.addFlags(1);
   }

   default void markNotBuilt() {
      this.removeFlags(1);
   }

   default boolean isBuilt() {
      return this.hasFlags(1);
   }

   default void markOptionalRendering() {
      this.addFlags(4);
   }

   default boolean isOptionalRendering() {
      return this.hasFlags(4);
   }

   IJavaDecompilableElement duplicate();
}
