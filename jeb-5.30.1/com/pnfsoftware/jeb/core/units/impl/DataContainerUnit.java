package com.pnfsoftware.jeb.core.units.impl;

import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Ser
public class DataContainerUnit extends ContainerUnit {
   private static final ILogger logger = GlobalLog.getLogger(DataContainerUnit.class);
   @SerTransient
   private DataContainerUnit.Entry root;

   public DataContainerUnit(DataContainerUnit.Entry var1, IUnitProcessor var2, IUnitCreator var3, IPropertyDefinitionManager var4) {
      super(var1.name, var2, var3, var4);
      this.root = var1;
   }

   @Override
   public boolean process() {
      return this.process(false);
   }

   @Override
   public boolean process(boolean var1) {
      if (this.isProcessed()) {
         return true;
      } else {
         register(this, this.root.children.values());
         this.setProcessed(true);
         return true;
      }
   }

   public static void register(IUnit var0, Collection var1) {
      register(var0, var1, false);
   }

   public static void register(IUnit var0, Collection var1, boolean var2) {
      for (DataContainerUnit.Entry var4 : var1) {
         Object var5;
         if (var4.isFolder()) {
            var5 = new DataContainerUnit(var4, var0.getUnitProcessor(), var0, var0.getPropertyDefinitionManager());
            ((IUnit)var5).process();
         } else {
            var5 = var0.getUnitProcessor().process(var4.name, new BytesInput(var4.data, 0, var4.data.length, var4.name), var0, null, false, !var2);
         }

         var0.addChild((IUnit)var5);
      }
   }

   @Ser
   public static class Entry {
      @SerId(1)
      private String name;
      @SerId(2)
      private byte[] data;
      @SerId(3)
      private Map children;

      public static DataContainerUnit.Entry create(String var0, byte[] var1) {
         if (var0 != null && var1 != null) {
            return new DataContainerUnit.Entry(var0, var1);
         } else {
            throw new IllegalArgumentException();
         }
      }

      public static DataContainerUnit.Entry createFolder(String var0) {
         if (var0 == null) {
            throw new IllegalArgumentException();
         } else {
            return new DataContainerUnit.Entry(var0, null);
         }
      }

      private Entry(String var1, byte[] var2) {
         this.name = var1;
         this.data = var2;
         if (var2 == null) {
            this.children = new LinkedHashMap();
         }
      }

      public boolean isFolder() {
         return this.data == null;
      }

      public String getName() {
         return this.name;
      }

      public byte[] getData() {
         return this.data;
      }

      public Map getChildren() {
         return this.children;
      }

      @Override
      public String toString() {
         return this.isFolder() ? Strings.ff("file(%d)", this.data.length) : Strings.ff("folder(%d)", this.children.size());
      }
   }
}
