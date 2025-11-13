package com.pnfsoftware.jeb.util.serialization.objects;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerConstructor;
import java.util.ArrayList;
import java.util.Collection;

@Ser
public final class SerArrayList extends ArrayList implements SerList {
   private static final long serialVersionUID = 1L;

   @SerConstructor
   public SerArrayList() {
   }

   public SerArrayList(Collection var1) {
      super(var1);
   }

   public SerArrayList(int var1) {
      super(var1);
   }
}
