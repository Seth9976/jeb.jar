package com.pnfsoftware.jeb.util.serialization;

import com.pnfsoftware.jeb.util.base.IProgressCallback;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.LEDataInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Deserializer {
   private int version;
   private int flags;
   private LEDataInputStream in;
   private ITypeIdProvider customTypeIdProvider;
   private List classloaders;
   private IInternalDeserializer d;
   private boolean initialized;
   private int rootCount;

   public Deserializer(ITypeIdProvider typeIdProvider, InputStream inputStream) {
      this(typeIdProvider, new ArrayList(), inputStream);
   }

   public Deserializer(ITypeIdProvider customTypeIdProvider, List classloaders, InputStream in) {
      this.in = new LEDataInputStream(new BufferedInputStream(in));
      this.customTypeIdProvider = customTypeIdProvider;
      this.classloaders = classloaders;
   }

   public boolean isInitialized() {
      return this.initialized;
   }

   public void initialize() throws IOException {
      this.readHeader();
   }

   public int getObjectCount() {
      this.verifyInitialized();
      return this.d.getObjectCount();
   }

   public Collection getObjects() {
      this.verifyInitialized();
      return this.d.getObjects();
   }

   public void setExpectedObjectCount(int expectedObjectCount) {
      this.verifyInitialized();
      this.d.setExpectedObjectCount(expectedObjectCount);
   }

   public void addProgressCallback(IProgressCallback progressCallback) {
      this.verifyInitialized();
      this.d.addProgressCallback(progressCallback);
   }

   public void removeProgressCallback(IProgressCallback progressCallback) {
      this.verifyInitialized();
      this.d.removeProgressCallback(progressCallback);
   }

   public Object deserialize() throws IOException {
      return this.deserialize(Object.class);
   }

   public Object deserialize(Class clazz) throws IOException {
      if (!this.initialized) {
         this.readHeader();
      }

      Object deserializeInternal = this.d.deserializeInternal();
      this.rootCount++;
      return deserializeInternal;
   }

   private void readHeader() throws IOException {
      if (this.initialized) {
         throw new IllegalStateException();
      } else {
         this.initialized = true;
         byte[] array = new byte[8];
         this.in.readFully(array);
         if (!"PNF-ORPD".equals(Strings.decodeASCII(array))) {
            throw new SerializationException("Invalid header for persisted data");
         } else {
            this.version = this.in.readByte() & 255;
            this.flags = this.in.readByte() & 255;
            this.in.readByte();
            this.in.readByte();
            switch (this.version) {
               case 1:
                  this.d = new Deserializer_v1_and_v2(false, this.customTypeIdProvider, this.classloaders, this.in);
                  break;
               case 2:
                  this.d = new Deserializer_v1_and_v2(true, this.customTypeIdProvider, this.classloaders, this.in);
                  break;
               case 3:
                  this.d = new Deserializer_v3(this.customTypeIdProvider, this.classloaders, this.in);
                  break;
               case 4:
                  this.d = new Deserializer_v4(this.customTypeIdProvider, this.classloaders, this.in);
                  break;
               case 5:
                  this.d = new Deserializer_v5(this.customTypeIdProvider, this.classloaders, this.in);
                  break;
               default:
                  throw new SerializationException("Unsupported persisted stream version number: " + this.version);
            }

            this.in.readInt();
         }
      }
   }

   public int getVersion() {
      this.verifyInitialized();
      return this.version;
   }

   public int getFlags() {
      this.verifyInitialized();
      return this.flags;
   }

   public boolean needsStringPool() {
      this.verifyInitialized();
      return this.version >= 4 && (this.flags & 1) != 0;
   }

   public void setStringPool(List list) {
      this.verifyInitialized();
      if (this.d instanceof Deserializer_v4 deserializer_v4) {
         deserializer_v4.setStringPool(list);
      } else {
         if (!(this.d instanceof Deserializer_v5)) {
            throw new RuntimeException();
         }

         ((Deserializer_v5)this.d).setStringPool(list);
      }
   }

   public void addObjectCreatedHook(Class clazz, IDeserializationEventHandler deserializationEventHandler) {
      this.verifyInitialized();
      this.d.addObjectCreatedHook(clazz, deserializationEventHandler);
   }

   public void removeObjectCreatedHook(Class clazz, IDeserializationEventHandler deserializationEventHandler) {
      this.verifyInitialized();
      this.d.removeObjectCreatedHook(clazz, deserializationEventHandler);
   }

   private void verifyInitialized() {
      if (!this.initialized) {
         throw new IllegalStateException("Deserializer must be initialized");
      }
   }
}
