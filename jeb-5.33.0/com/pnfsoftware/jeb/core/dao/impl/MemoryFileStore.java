package com.pnfsoftware.jeb.core.dao.impl;

import com.pnfsoftware.jeb.core.dao.IFileStore;
import com.pnfsoftware.jeb.util.encoding.Hash;
import com.pnfsoftware.jeb.util.format.Formatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryFileStore implements IFileStore {
   private Map files = new HashMap();

   @Override
   public String getStoreLocation() {
      return null;
   }

   @Override
   public boolean has(String var1) {
      return this.files.containsKey(var1);
   }

   @Override
   public boolean remove(String var1) {
      return this.files.remove(var1) != null;
   }

   @Override
   public byte[] get(String var1) {
      return (byte[])this.files.get(var1);
   }

   @Override
   public String put(byte[] var1) {
      return this.put(null, var1);
   }

   @Override
   public String put(String var1, byte[] var2) {
      if (var1 == null) {
         var1 = Formatter.byteArrayToHexString(Hash.calculateSHA256(var2));
      }

      this.files.put(var1, var2);
      return var1;
   }

   @Override
   public List list() {
      return new ArrayList(this.files.keySet());
   }
}
