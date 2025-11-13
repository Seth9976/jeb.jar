package com.pnfsoftware.jeb.core.units.impl;

import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.output.IUnitDocumentPresentation;
import com.pnfsoftware.jeb.core.output.IUnitFormatter;
import com.pnfsoftware.jeb.core.output.UnitFormatterUtil;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.units.IQuickStateObject;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitLock;
import com.pnfsoftware.jeb.core.units.IUnitNotificationManager;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.IUnitProvider;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.events.IEventSource;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.LK;
import java.util.List;
import java.util.Map;

@Ser
public class WrapperUnit implements IUnit, LK {
   @SerId(1)
   private IUnit wrap;
   @SerId(2)
   private IUnitProvider provider;

   public WrapperUnit(IUnit var1, IUnitProvider var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.wrap = var1;
         this.provider = var2;
      }
   }

   public IUnit getWrap() {
      return this.wrap;
   }

   @Override
   public void addListener(IEventListener var1) {
      this.wrap.addListener(var1);
   }

   @Override
   public void insertListener(int var1, IEventListener var2) {
      this.wrap.insertListener(var1, var2);
   }

   @Override
   public int countListeners() {
      return this.wrap.countListeners();
   }

   @Override
   public List getListeners() {
      return this.wrap.getListeners();
   }

   @Override
   public IEventSource getParentSource() {
      return this.wrap.getParentSource();
   }

   @Override
   public void notifyListeners(IEvent var1) {
      this.wrap.notifyListeners(var1);
   }

   @Override
   public boolean canBePersisted() {
      return this.wrap.canBePersisted();
   }

   @Override
   public List getChildren() {
      return this.wrap.getChildren();
   }

   @Override
   public IUnitLock getLock() {
      return this.wrap.getLock();
   }

   @Override
   public long getCreationTimestamp() {
      return this.wrap.getCreationTimestamp();
   }

   @Override
   public String getDescription() {
      if (this.provider != null) {
         String var1 = this.provider.getDescription();
         if (var1 != null) {
            return var1;
         }
      }

      return this.wrap.getDescription();
   }

   @Override
   public String getFormatType() {
      if (this.provider != null) {
         String var1 = this.provider.getFormatType();
         if (var1 != null) {
            return var1;
         }
      }

      return this.wrap.getFormatType();
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = this.wrap.getFormatter();
      if (this.provider.getFormatter() != null) {
         IUnitFormatter var2 = this.provider.getFormatter();
         List var3 = var2.getPresentations();
         if (var3 != null && !var3.isEmpty()) {
            for (int var4 = 0; var4 < var3.size(); var4++) {
               if (UnitFormatterUtil.getPresentationByIdentifier(var1, ((IUnitDocumentPresentation)var3.get(var4)).getId()) == null) {
                  var1.addPresentation((IUnitDocumentPresentation)var3.get(var4), var2.isPersisted(var4));
               }
            }
         }
      }

      return var1;
   }

   @Override
   public byte[] getIconData() {
      return this.wrap.getIconData();
   }

   @Override
   public long getUid() {
      return this.wrap.getUid();
   }

   @Override
   public String getName() {
      return this.wrap.getName();
   }

   @Override
   public String getRealName() {
      return this.wrap.getRealName();
   }

   @Override
   public void setRealName(String var1) {
      this.wrap.setRealName(var1);
   }

   @Override
   public String getNotes() {
      return this.wrap.getNotes();
   }

   @Override
   public IUnitNotificationManager getNotificationManager() {
      return this.wrap.getNotificationManager();
   }

   @Override
   public IUnitCreator getParent() {
      return this.wrap.getParent();
   }

   @Override
   public IPropertyDefinitionManager getPropertyDefinitionManager() {
      return this.wrap.getPropertyDefinitionManager();
   }

   @Override
   public IPropertyManager getPropertyManager() {
      return this.wrap.getPropertyManager();
   }

   @Override
   public String getStatus() {
      return this.wrap.getStatus();
   }

   @Override
   public IUnitProcessor getUnitProcessor() {
      return this.wrap.getUnitProcessor();
   }

   @Override
   public boolean isProcessed() {
      return this.wrap.isProcessed();
   }

   @Override
   public boolean isStale() {
      return this.wrap.isStale();
   }

   @Override
   public boolean process() {
      return this.wrap.process();
   }

   @Override
   public void removeListener(IEventListener var1) {
      this.wrap.removeListener(var1);
   }

   @Override
   public void setName(String var1) {
      this.wrap.setName(var1);
   }

   @Override
   public void setNotes(String var1) {
      this.wrap.setNotes(var1);
   }

   @Override
   public void setParent(IUnitCreator var1) {
      this.wrap.setParent(var1);
   }

   @Override
   public void setParentSource(IEventSource var1) {
      this.wrap.setParentSource(var1);
   }

   @Override
   public void initializePropertyObjects(IUnitCreator var1, IUnitProcessor var2, IPropertyDefinitionManager var3) {
      this.wrap.initializePropertyObjects(var1, var2, var3);
   }

   @Override
   public void postDeserialization(IRuntimeProject var1) {
      this.wrap.postDeserialization(var1);
   }

   @Override
   public void setUnitProcessor(IUnitProcessor var1) {
      this.wrap.setUnitProcessor(var1);
   }

   @Override
   public List getContributions() {
      return this.wrap.getContributions();
   }

   @Override
   public List getInterpreters() {
      return this.wrap.getInterpreters();
   }

   @Override
   public void dispose() {
      this.wrap.dispose();
   }

   @Override
   public boolean isDisposed() {
      return this.wrap.isDisposed();
   }

   @Override
   public void removeChild(IUnit var1) {
      this.wrap.removeChild(var1);
   }

   @Override
   public void addChild(IUnit var1, boolean var2) {
      this.wrap.addChild(var1, var2);
   }

   @Override
   public boolean isTransientChild(IUnit var1) {
      return this.wrap.isTransientChild(var1);
   }

   @Override
   public void addChild(IUnit var1) {
      this.wrap.addChild(var1);
   }

   @Override
   public IQuickStateObject generateQuickState() {
      return this.wrap.generateQuickState();
   }

   @Override
   public void setData(Object var1, Object var2, boolean var3) {
      this.wrap.setData(var1, var2, var3);
   }

   @Override
   public Object getData(Object var1) {
      return this.wrap.getData(var1);
   }

   @Override
   public void clearAllData(Object var1) {
      this.wrap.clearAllData(var1);
   }

   @Override
   public Map getAllData() {
      return this.wrap.getAllData();
   }
}
