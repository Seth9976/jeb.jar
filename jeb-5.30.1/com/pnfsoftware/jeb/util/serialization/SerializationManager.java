package com.pnfsoftware.jeb.util.serialization;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class SerializationManager {
   public static final String MAGIC = "PNF-ORPD";
   public static final int FLAG_HAS_STRINGPOOL = 1;
   private ITypeIdProvider typeIdProvider;
   private List classloaders = new ArrayList();

   public SerializationManager(ITypeIdProvider var1) {
      this.typeIdProvider = var1;
   }

   public void addClassloader(ClassLoader var1) {
      if (var1 != null && !this.classloaders.contains(var1)) {
         this.classloaders.add(var1);
      }
   }

   public ITypeIdProvider getTypeIdProvider() {
      return this.typeIdProvider;
   }

   public Serializer getSerializer(OutputStream var1) {
      return this.getSerializer(var1, false);
   }

   public Serializer getSerializer(OutputStream var1, boolean var2) {
      return new Serializer(this.typeIdProvider, var1, var2);
   }

   public Serializer_v4 getSerializer_v4(OutputStream var1) {
      return new Serializer_v4(this.typeIdProvider, var1, false);
   }

   public Deserializer getDeserializer(InputStream var1) {
      return new Deserializer(this.typeIdProvider, this.classloaders, var1);
   }
}
