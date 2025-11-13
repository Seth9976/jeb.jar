package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.BookmarkManager;
import com.pnfsoftware.jeb.core.IArtifact;
import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.ILiveArtifact;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.IUserDataSupport;
import com.pnfsoftware.jeb.core.RuntimeProjectUtil;
import com.pnfsoftware.jeb.core.UserDataSupport;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.events.JebEventSource;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.input.FileInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IConfiguration;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.properties.impl.ConfigurationMemoryMap;
import com.pnfsoftware.jeb.core.properties.impl.PropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeInteger;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeSelection;
import com.pnfsoftware.jeb.core.units.IQuickStateObject;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.util.collect.WeakIdentityHashMap;
import com.pnfsoftware.jeb.util.encoding.HashCalculator;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.SizeFormatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Ser
public class Or extends JebEventSource implements IRuntimeProject {
   private static final ILogger ld = GlobalLog.getLogger(Or.class);
   @SerTransient
   private long gp;
   @SerTransient
   private Nj oT;
   @SerTransient
   private XR fI;
   @SerTransient
   private IPropertyDefinitionManager WR;
   @SerTransient
   private PropertyManager NS;
   @SerTransient
   private boolean vP;
   @SerTransient
   boolean pC;
   @SerTransient
   public boolean A;
   @SerTransient
   String kS;
   @SerId(1)
   private String xC;
   @SerId(2)
   private String ED;
   @SerId(3)
   private String Sc;
   @SerId(4)
   private long ah;
   @SerId(5)
   private long eP;
   @SerId(6)
   private List UO;
   @SerId(7)
   IConfiguration wS;
   @SerId(8)
   private Map Ab = new HashMap();
   @SerId(9)
   WeakIdentityHashMap UT = new WeakIdentityHashMap();
   @SerId(10)
   List E = new ArrayList();
   @SerId(11)
   private IUserDataSupport rl = new UserDataSupport();
   @SerId(12)
   int sY;
   @SerId(13)
   BookmarkManager ys = new BookmarkManager();

   @SerCustomInitPostGraph
   private void A() {
      if (this.rl == null) {
         this.rl = new UserDataSupport();
      }

      this.vP = true;
      this.UT = new WeakIdentityHashMap();
      this.E = new ArrayList();
      if (this.ys == null) {
         this.ys = new BookmarkManager();
      }

      this.addListener(this.ys);
   }

   Or(String s) {
      this.xC = s;
      this.ED = s;
      this.Sc = "";
      this.ah = System.currentTimeMillis();
      this.eP = 0L;
      this.UO = new ArrayList();
      this.addListener(this.ys = new BookmarkManager());
   }

   @Override
   public void notifyListeners(JebEvent jebEvent, boolean b) {
      if (jebEvent != null && jebEvent.getType() == J.UnitProcessed && jebEvent.getSource() instanceof IUnit) {
         IUnit unit = (IUnit)jebEvent.getSource();
         if (unit.isProcessed()) {
            IQuickStateObject ws = (IQuickStateObject)this.UT.remove(unit);
            if (ws == null && !this.E.isEmpty()) {
               for (IQuickStateObject quickStateObject : this.E) {
                  if (quickStateObject.isTargetUnit(unit)) {
                     ws = quickStateObject;
                     break;
                  }
               }

               if (ws != null) {
                  this.E.remove(ws);
               }
            }

            if (ws != null) {
               if (unit instanceof C && !((C)unit).isAnalysisCompleted()) {
                  ((C)unit).wS = ws;
               } else {
                  try {
                     ws.reload(unit);
                  } catch (Exception var7) {
                     ld.error(S.L("%s: Attempt to reload a quick-state failed"), UnitUtil.buildFullyQualifiedUnitPath(unit));
                  }
               }
            }
         }
      }

      super.notifyListeners(jebEvent, b);
   }

   void pC(Nj ot, IPropertyDefinitionManager propertyDefinitionManager, boolean b) {
      this.oT = ot;
      this.gp = System.currentTimeMillis();
      if (this.xC != null) {
         int lastIndexOf2 = Strings.lastIndexOf2(this.xC, '/', '\\');
         if (lastIndexOf2 >= 0) {
            String substring = this.xC.substring(lastIndexOf2 + 1);
            if (!substring.isEmpty()) {
               this.xC = substring;
            }
         }
      }

      if (this.ED != null) {
         int lastIndexOf3 = Strings.lastIndexOf2(this.ED, '/', '\\');
         if (lastIndexOf3 >= 0) {
            String substring2 = this.ED.substring(lastIndexOf3 + 1);
            if (!substring2.isEmpty()) {
               this.ED = substring2;
            }
         }
      }

      this.WR = (IPropertyDefinitionManager)(propertyDefinitionManager != null
         ? propertyDefinitionManager
         : new PropertyDefinitionManager(null, null, S.L("Project-specific properties. Modifications will not affect your global settings."), 0));
      PropertyDefinitionManager propertyDefinitionManager2 = new PropertyDefinitionManager("project", this.WR);
      propertyDefinitionManager2.addDefinition(
         "ArtifactProcessingDepth",
         PropertyTypeInteger.createPositive(20),
         S.L("Determine the maximum depth an input artifact (e.g., a file) will be explored to create units and sub-units representing analysis entities"),
         40
      );
      propertyDefinitionManager2.addDefinition(
         "AlwaysProcessDuplicateInputs",
         PropertyTypeBoolean.create(false),
         S.L("Process binary inputs even if such input was seen earlier and processed as another unit already"),
         8
      );
      propertyDefinitionManager2.addDefinition("CompressPersistedProject", PropertyTypeBoolean.create(true), S.L("Compress the JDB2 database (recommended)"), 8);
      propertyDefinitionManager2.addDefinition(
         "PersistenceStrategy",
         PropertyTypeSelection.Builder.create()
            .addDefaultEntry(0, S.L("Automatic"), S.L("Defaults to full-save unless quick-save is a viable alternative, which may be recommended to the user"))
            .addEntry(1, S.L("Full save"), S.L("Always full-save JDB2"))
            .addEntry(2, S.L("Quick save"), S.L("Always quick-save JDB2 (not recommended)"))
            .build(),
         S.L("Determine how a project will be saved to JDB2"),
         8
      );
      propertyDefinitionManager2.addDefinition(
         "PersistArtifactFiles",
         PropertyTypeBoolean.create(true),
         S.L(
            "Persist the full artifact files in a JDB2 database. The resulting JDB2 file will be larger since the input files are embedded in it for extra safety."
         ),
         8
      );
      if (this.wS == null) {
         IConfiguration configuration = ot.getPropertyManager().getConfiguration();
         if (configuration != null) {
            this.wS = new ConfigurationMemoryMap(configuration);
         } else {
            this.wS = new ConfigurationMemoryMap();
         }
      }

      this.NS = new PropertyManager(this.WR, this.wS, ot.getPropertyManager());
      (this.fI = new XR(true, this.WR, this.NS)).pC(this.NS.getBoolean(".project.AlwaysProcessDuplicateInputs"));

      for (rm rm : this.UO) {
         IArtifact artifact = rm.getArtifact();
         if (this.kS != null && artifact.getInput() instanceof FileInput) {
            FileInput fileInput = (FileInput)artifact.getInput();
            if (fileInput.getPersistedPath() != null && new File(fileInput.getPersistedPath()).isAbsolute()) {
               fileInput.setBase(this.kS);
            } else {
               fileInput.rebase(this.kS);
            }
         }

         artifact.setParentSource(this);
         Iterator iterator2 = rm.getUnits().iterator();

         while (iterator2.hasNext()) {
            this.pC((IUnit)iterator2.next(), artifact, b);
         }

         this.notifyListeners(new JebEvent(J.ArtifactProcessed, rm));
      }
   }

   boolean x() {
      try {
         return this.getPropertyManager().getBoolean(".project.CompressPersistedProject");
      } catch (Exception var2) {
         return false;
      }
   }

   private void pC(IUnit unit, IUnitCreator unitCreator, boolean b) {
      unit.setUnitProcessor(this.fI);
      unit.setParent(unitCreator);
      unit.setParentSource(unitCreator);
      IPropertyDefinitionManager propertyDefinitionManager = null;
      if (unit instanceof cdw) {
         propertyDefinitionManager = this.fI.A();
      } else {
         IUnitIdentifier unitIdentifier = this.fI.getUnitIdentifier(unit.getFormatType());
         if (unitIdentifier != null) {
            propertyDefinitionManager = unitIdentifier.getPropertyDefinitionManager();
         } else {
            for (IUnitCreator parent = unitCreator; parent instanceof IUnit; parent = ((IUnit)parent).getParent()) {
               propertyDefinitionManager = ((IUnit)parent).getPropertyDefinitionManager();
               if (propertyDefinitionManager != null) {
                  break;
               }
            }
         }
      }

      unit.initializePropertyObjects(unitCreator, this.fI, propertyDefinitionManager);
      if (b) {
         unit.postDeserialization(this);
      }

      Iterator iterator = unit.getChildren().iterator();

      while (iterator.hasNext()) {
         this.pC((IUnit)iterator.next(), unit, b);
      }
   }

   @Override
   public int getPersistenceStrategy() {
      return this.getPropertyManager().getInteger(".project.PersistenceStrategy");
   }

   @Override
   public void setPersistenceStrategy(int i) {
      this.getPropertyManager().setInteger(".project.PersistenceStrategy", i);
   }

   public Nj pC() {
      return this.oT;
   }

   @Override
   public IPropertyDefinitionManager getPropertyDefinitionManager() {
      return this.NS.getPropertyDefinitionManager();
   }

   @Override
   public IPropertyManager getPropertyManager() {
      return this.NS;
   }

   @Override
   public String getKey() {
      return this.xC;
   }

   @Override
   public String getPersistenceKey() {
      return this.pC().pC(this);
   }

   @Override
   public boolean isReloaded() {
      return this.vP;
   }

   @Override
   public String getName() {
      return this.ED;
   }

   @Override
   public void setName(String ed) {
      if (ed != null && !ed.equals(this.ED)) {
         this.ED = ed;
         this.notifyListeners(new JebEvent(J.ProjectPropertyChanged));
      }
   }

   @Override
   public String getNotes() {
      return this.Sc;
   }

   @Override
   public void setNotes(String sc) {
      if (sc != null && !sc.equals(this.Sc)) {
         this.Sc = sc;
         this.notifyListeners(new JebEvent(J.ProjectPropertyChanged));
      }
   }

   @Override
   public IUnitProcessor getProcessor() {
      return this.fI;
   }

   @Override
   public long getCreationTimestamp() {
      return this.ah;
   }

   @Override
   public long getRecordedTimestamp() {
      return this.eP;
   }

   @Override
   public long getUptime() {
      return System.currentTimeMillis() - this.gp;
   }

   void hw() {
      this.eP = System.currentTimeMillis();
   }

   @Override
   public void close() {
      Object[] var10000 = new Object[]{this.getName()};
      this.notifyListeners(new JebEvent(J.ProjectClosing, this));
      if (this.ys != null) {
         this.removeListener(this.ys);
      }

      Iterator iterator = this.UO.iterator();

      while (iterator.hasNext()) {
         Iterator iterator2 = ((rm)iterator.next()).getUnits().iterator();

         while (iterator2.hasNext()) {
            this.destroyUnit((IUnit)iterator2.next());
         }
      }

      this.ah = 0L;
      this.xC = null;
      this.oT = null;
      this.fI = null;
      this.UO = null;
      this.WR = null;
      this.NS.dispose();
      this.NS = null;
   }

   @Override
   public boolean isClosed() {
      return this.UO == null;
   }

   public rm pC(IArtifact artifact) {
      return this.pC(artifact, null, false, false, false, true);
   }

   public rm pC(IArtifact artifact, String s, boolean b, boolean b2) {
      return this.pC(artifact, s, b, b2, false, true);
   }

   rm pC(IArtifact artifact, String s, boolean b, boolean b2, boolean b3, boolean b4) {
      if (!b3 && !this.oT.pC().kS().wS()) {
         throw new JebRuntimeException("Currently, your license does not permit the addition of artifacts to projects");
      } else {
         if (artifact.getInput() instanceof FileInput fileInput) {
            if (this.kS == null) {
               File file = fileInput.getFile();
               if (file != null) {
                  this.kS = file.getParent();
               }
            } else {
               fileInput.setBase(this.kS);
            }
         }

         artifact.setParentSource(this);
         rm rm = new rm(this, artifact);
         this.UO.add(rm);
         rm.load(s, b, b2);
         if (!b2 && b4) {
            this.notifyListeners(new JebEvent(J.ArtifactProcessed, rm));
         }

         return rm;
      }
   }

   @Override
   public void finishArtifactProcessing(ILiveArtifact liveArtifact) {
      int n = 0;

      for (IUnit unit : liveArtifact.getUnits()) {
         if (!unit.isProcessed()) {
            unit.process();
            n++;
         }
      }

      if (n > 0) {
         this.notifyListeners(new JebEvent(J.ArtifactProcessed, liveArtifact));
      }
   }

   @Override
   public boolean hasLiveArtifacts() {
      return this.UO != null && !this.UO.isEmpty();
   }

   @Override
   public List getLiveArtifacts() {
      return (List)(this.UO == null ? Collections.emptyList() : new ArrayList(this.UO));
   }

   @Override
   public ILiveArtifact getLiveArtifact(int n) {
      if (this.UO == null) {
         return null;
      } else {
         return n >= 0 && n < this.UO.size() ? (ILiveArtifact)this.UO.get(n) : null;
      }
   }

   @Override
   public int getArtifactCount() {
      return this.UO == null ? 0 : this.UO.size();
   }

   @Override
   public boolean destroyUnit(IUnit unit) {
      IUnitCreator parent = unit.getParent();
      if (parent instanceof IArtifact artifact) {
         this.A(artifact).pC(unit);
      } else if (parent instanceof IUnit unit2) {
         unit2.removeChild(unit);
      }

      return true;
   }

   public boolean pC(ILiveArtifact liveArtifact) {
      int index = this.UO.indexOf(liveArtifact);
      if (index < 0) {
         return false;
      } else {
         for (IUnit unit : liveArtifact.getUnits()) {
            if (!this.destroyUnit(unit)) {
               ld.error(S.L("Cannot destroy unit %s"), unit);
               return false;
            }
         }

         this.UO.remove(index);
         this.notifyListeners(new JebEvent(J.ArtifactDestroyed, liveArtifact));
         return true;
      }
   }

   private rm A(IArtifact artifact) {
      for (rm rm : this.UO) {
         if (rm.getArtifact() == artifact) {
            return rm;
         }
      }

      return null;
   }

   public List pC(IInput input, List list) {
      try {
         if (this.Ab == null) {
            return null;
         } else if (input == null) {
            return null;
         } else {
            long currentSize = input.getCurrentSize();
            if (currentSize < 0L) {
               return null;
            } else {
               List list2 = (List)this.Ab.get(currentSize);
               if (list2 == null) {
                  list2 = new ArrayList();
                  this.Ab.put(currentSize, list2);
               }

               ByteBuffer header = input.getHeader();
               if (header != null && header.remaining() >= 8) {
                  long long1 = header.getLong();
                  boolean b = currentSize > 524288000L;
                  if (b) {
                     ld.warn(S.L("Calculating hash of large file (%s), be patient..."), SizeFormatter.formatByteSize(currentSize));
                  }

                  byte[] sha256;
                  try {
                     label117: {
                        List pe2;
                        try (InputStream stream = input.getStream()) {
                           sha256 = HashCalculator.sha256(stream);
                           if (sha256 != null) {
                              break label117;
                           }

                           List list3 = null;
                           if (stream != null) {
                              stream.close();
                           }

                           pe2 = list3;
                        }

                        return pe2;
                     }
                  } catch (IOException var16) {
                     return null;
                  }

                  if (b) {
                     ld.info(S.L("Hash calculation is complete (%s)"), Formatter.byteArrayToHex(sha256));
                  }

                  for (Object pe : list2) {
                     if (((Pe)pe).A == long1 && Arrays.equals(((Pe)pe).kS, sha256)) {
                        Pe pe2 = (Pe)pe;
                        pe2.wS++;
                        return new ArrayList(((Pe)pe).UT);
                     }
                  }

                  list2.add(new Pe(currentSize, long1, sha256, list));
                  return list;
               } else {
                  return null;
               }
            }
         }
      } catch (Exception var17) {
         ld.catchingSilent(var17);
         return null;
      }
   }

   @Override
   public List getInputRecords() {
      if (this.Ab == null) {
         return null;
      } else {
         ArrayList list = new ArrayList();
         Iterator iterator = this.Ab.values().iterator();

         while (iterator.hasNext()) {
            list.addAll((Collection)iterator.next());
         }

         return list;
      }
   }

   @Override
   public List findUnits(Class clazz) {
      return RuntimeProjectUtil.findUnitsByType(this, clazz, false);
   }

   @Override
   public IUnit findUnit(Class clazz) {
      return RuntimeProjectUtil.findFirstUnitByType(this, clazz, false);
   }

   @Override
   public void setData(Object o, Object o2, boolean b) {
      this.rl.setData(o, o2, b);
   }

   @Override
   public Object getData(Object o) {
      return this.rl.getData(o);
   }

   @Override
   public void clearAllData(Object o) {
      this.rl.clearAllData(o);
   }

   @Override
   public Map getAllData() {
      return this.rl.getAllData();
   }

   @Override
   public String toString() {
      return Strings.ff("Project:{%s}", this.getName());
   }

   @Override
   public BookmarkManager getBookmarkManager() {
      return this.ys;
   }

   @Override
   public ILiveArtifact processArtifact(IArtifact artifact, String s, boolean b, boolean b2) {
      return this.pC(artifact, s, b, b2);
   }

   @Override
   public ILiveArtifact processArtifact(IArtifact artifact) {
      return this.pC(artifact);
   }

   @Override
   public IEnginesContext getEnginesContext() {
      return this.pC();
   }
}
