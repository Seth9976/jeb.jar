package com.pnfsoftware.jeb.core.output;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public abstract class AbstractUnitRepresentation implements IUnitDocumentPresentation {
   @SerId(1)
   private long id;
   @SerId(2)
   private String label;
   @SerId(3)
   private boolean defaultRepresentation;

   public AbstractUnitRepresentation(String var1) {
      this(var1, false);
   }

   public AbstractUnitRepresentation(String var1, boolean var2) {
      this(var1 == null ? "".hashCode() : var1.hashCode(), var1, var2);
   }

   public AbstractUnitRepresentation(long var1, String var3, boolean var4) {
      this.id = var1;
      if (var3 == null) {
         throw new IllegalArgumentException("label of an IUnitDocumentPresentation can not be null");
      } else {
         this.label = var3;
         this.defaultRepresentation = var4;
      }
   }

   @Override
   public long getId() {
      return this.id;
   }

   @Override
   public String getLabel() {
      return this.label;
   }

   @Override
   public boolean isDefaultRepresentation() {
      return this.defaultRepresentation;
   }

   @Override
   public String toString() {
      return Strings.ff("id:%d,label:\"%s\",default:%b,doc:{%s}", this.getId(), this.getLabel(), this.isDefaultRepresentation(), this.getDocument());
   }
}
