package com.pnfsoftware.jeb.core.dao;

import java.util.List;

public interface IFileStore {
   String getStoreLocation();

   boolean has(String var1);

   boolean remove(String var1);

   byte[] get(String var1);

   String put(String var1, byte[] var2);

   String put(byte[] var1);

   List list();
}
