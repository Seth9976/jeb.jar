package com.pnfsoftware.jeb.core.output;

import com.pnfsoftware.jeb.util.collect.WeakPseudoList;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public abstract class AbstractTransientUnitRepresentation extends AbstractUnitRepresentation {
   private WeakPseudoList createdDocs = new WeakPseudoList();

   public AbstractTransientUnitRepresentation(String var1) {
      super(var1);
   }

   public AbstractTransientUnitRepresentation(String var1, boolean var2) {
      super(var1, var2);
   }

   public AbstractTransientUnitRepresentation(long var1, String var3, boolean var4) {
      super(var1, var3, var4);
   }

   @Override
   public IGenericDocument getDocument() {
      if (this.createdDocs == null) {
         throw new IllegalStateException("Disposed");
      } else {
         IGenericDocument var1 = this.createDocument();
         this.createdDocs.add(var1);
         return var1;
      }
   }

   protected abstract IGenericDocument createDocument();

   @Override
   public void dispose() {
      if (this.createdDocs != null) {
         for (; !this.createdDocs.isEmpty(); this.createdDocs.remove(0)) {
            IGenericDocument var1 = (IGenericDocument)this.createdDocs.get(0);
            if (var1 != null) {
               var1.dispose();
            }
         }

         this.createdDocs = null;
      }
   }
}
