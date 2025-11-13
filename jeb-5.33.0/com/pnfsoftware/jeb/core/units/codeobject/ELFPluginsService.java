package com.pnfsoftware.jeb.core.units.codeobject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ELFPluginsService {
   private static volatile ELFPluginsService instance;
   private List relocationContexts = new ArrayList();
   private List sectionProcessors = new ArrayList();
   private List symbolProcessorFactories = new ArrayList();

   public static ELFPluginsService getInstance() {
      synchronized (ELFPluginsService.class) {
         if (instance == null) {
            instance = new ELFPluginsService();
         }
      }

      return instance;
   }

   public void addRelocationContext(ELFRelocationContext var1) {
      if (var1 != null && !this.relocationContexts.contains(var1)) {
         this.relocationContexts.add(var1);
      }
   }

   public List getRelocationContexts() {
      return Collections.unmodifiableList(this.relocationContexts);
   }

   public void addSectionProcessor(IELFSectionProcessor var1) {
      if (var1 != null && !this.sectionProcessors.contains(var1)) {
         this.sectionProcessors.add(var1);
      }
   }

   public List getSectionProcessors() {
      return Collections.unmodifiableList(this.sectionProcessors);
   }

   public void addSymbolsProcessorFactory(IELFSymbolProcessorFactory var1) {
      if (var1 != null && !this.symbolProcessorFactories.contains(var1)) {
         this.symbolProcessorFactories.add(var1);
      }
   }

   public List getSymbolProcessorFactories() {
      return Collections.unmodifiableList(this.symbolProcessorFactories);
   }

   public IELFSymbolProcessor createSymbolProcessor(IELFUnit var1) {
      for (IELFSymbolProcessorFactory var3 : this.symbolProcessorFactories) {
         if (var3.canProcess(var1)) {
            return var3.createInstance(var1);
         }
      }

      return null;
   }
}
