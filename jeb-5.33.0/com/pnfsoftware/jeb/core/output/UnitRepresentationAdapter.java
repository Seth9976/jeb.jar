package com.pnfsoftware.jeb.core.output;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class UnitRepresentationAdapter extends AbstractUnitRepresentation {
   @SerId(1)
   private IGenericDocument doc;

   public UnitRepresentationAdapter(long var1, String var3, boolean var4, IGenericDocument var5) {
      super(var1, var3, var4);
      if (var5 == null) {
         throw new IllegalArgumentException("Provide a non-null document");
      } else {
         this.doc = var5;
      }
   }

   @Override
   public IGenericDocument getDocument() {
      return this.doc;
   }

   @Override
   public void dispose() {
      this.doc.dispose();
   }
}
