package com.pnfsoftware.jeb.core.units.impl;

import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.IDataProvider;
import com.pnfsoftware.jeb.core.input.LazyInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.UnknownBinaryUnit;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Ser
public class LazyDataContainerUnit extends ContainerUnit {
   private static final ILogger logger = GlobalLog.getLogger(LazyDataContainerUnit.class);
   @SerId(1)
   private LazyDataContainerUnit.Entry root;

   public LazyDataContainerUnit(LazyDataContainerUnit.Entry var1, IUnitProcessor var2, IUnitCreator var3, IPropertyDefinitionManager var4) {
      super(var1.name, var2, var3, var4);
      this.root = var1;
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
      for (LazyDataContainerUnit.Entry var3 : var1) {
         Object var4;
         if (var3.isFolder()) {
            var4 = new LazyDataContainerUnit(var3, var0.getUnitProcessor(), var0, var0.getPropertyDefinitionManager());
            ((IUnit)var4).process();
         } else {
            LazyInput var5 = new LazyInput(var3.prv, var3.entryName, var3.entrySize, var3.index);
            var4 = new UnknownBinaryUnit(var3.name, var5, var0.getUnitProcessor(), var0, var0.getPropertyDefinitionManager());
         }

         var0.addChild((IUnit)var4);
      }
   }

   @Ser
   public static class Entry {
      @SerId(1)
      private IDataProvider prv;
      @SerId(2)
      private String entryName;
      @SerId(3)
      private long entrySize;
      @SerId(4)
      private String name;
      @SerId(5)
      private Map children = new LinkedHashMap();
      @SerId(6)
      private byte[] data;
      @SerId(7)
      private int index = 0;

      public static LazyDataContainerUnit.Entry create(IDataProvider var0, String var1, long var2, String var4, int var5) {
         return new LazyDataContainerUnit.Entry(var0, var1, var2, var4, var5);
      }

      public static LazyDataContainerUnit.Entry createFolder(String var0) {
         return new LazyDataContainerUnit.Entry(null, null, 0L, var0, 0);
      }

      private Entry(IDataProvider var1, String var2, long var3, String var5, int var6) {
         this.prv = var1;
         this.entryName = var2;
         this.entrySize = var3;
         this.name = var5;
         this.index = var6;
      }

      public boolean isFolder() {
         return this.entryName == null;
      }

      public String getName() {
         return this.name;
      }

      public int getIndex() {
         return this.index;
      }

      public Map getChildren() {
         return this.children;
      }
   }
}
