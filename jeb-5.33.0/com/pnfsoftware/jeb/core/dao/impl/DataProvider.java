package com.pnfsoftware.jeb.core.dao.impl;

import com.pnfsoftware.jeb.core.dao.IApplicationDatabase;
import com.pnfsoftware.jeb.core.dao.IDataProvider;
import com.pnfsoftware.jeb.core.dao.IFileDatabase;
import com.pnfsoftware.jeb.core.dao.IFileStore;
import com.pnfsoftware.jeb.core.dao.IUserDatabase;
import com.pnfsoftware.jeb.core.properties.IConfiguration;

public class DataProvider implements IDataProvider {
   private IUserDatabase userdb;
   private IFileDatabase projectdb;
   private IFileStore filestore;
   private IFileStore pluginstore;
   private IApplicationDatabase appdb;
   private IConfiguration config;

   public DataProvider(IUserDatabase var1, IFileDatabase var2, IFileStore var3, IFileStore var4, IApplicationDatabase var5, IConfiguration var6) {
      this.userdb = var1;
      this.projectdb = var2;
      this.filestore = var3;
      this.pluginstore = var4;
      this.appdb = var5;
      this.config = var6;
   }

   @Override
   public IUserDatabase getUserDatabase() {
      return this.userdb;
   }

   @Override
   public IFileDatabase getProjectDatabase() {
      return this.projectdb;
   }

   @Override
   public IFileStore getFileStore() {
      return this.filestore;
   }

   @Override
   public IFileStore getPluginStore() {
      return this.pluginstore;
   }

   @Override
   public IApplicationDatabase getApplicationDatabase() {
      return this.appdb;
   }

   @Override
   public IConfiguration getConfiguration() {
      return this.config;
   }
}
