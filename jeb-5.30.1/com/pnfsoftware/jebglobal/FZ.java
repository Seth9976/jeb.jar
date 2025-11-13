package com.pnfsoftware.jebglobal;

public enum FZ {
   q(0, "No error has occurred."),
   RF(10, "Passed thread is null, is not a valid thread or has exited."),
   xK(11, "Thread group invalid."),
   Dw(12, "Invalid priority."),
   Uv(13, "If the specified thread has not been suspended by an event."),
   oW(14, "Thread already suspended."),
   gO(15, "Thread has not been started or is now dead."),
   nf(20, "If this reference type has been unloaded and garbage collected."),
   gP(21, "Invalid class."),
   za(22, "Class has been loaded but not yet prepared."),
   lm(23, "Invalid method."),
   zz(24, "Invalid location."),
   JY(25, "Invalid field."),
   HF(30, "Invalid jframeID."),
   LK(31, "There are no more Java or JNI frames on the call stack."),
   io(32, "Information about the frame is not available."),
   qa(33, "Operation can only be performed on current frame."),
   Hk(34, "The variable is not an appropriate type for the function used."),
   Me(35, "Invalid slot."),
   PV(40, "Item already set."),
   oQ(41, "Desired element not found."),
   xW(50, "Invalid monitor."),
   KT(51, "This thread doesn't own the monitor."),
   Gf(52, "The call has been interrupted before completion."),
   Ef(60, "The virtual machine attempted to read a class file and determined that the file is malformed or otherwise cannot be interpreted as a class file."),
   cC(61, "A circularity has been detected while initializing a class."),
   sH(62, "The verifier detected that a class file, though well formed, contained some sort of internal inconsistency or security problem."),
   CE(63, "Adding methods has not been implemented."),
   wF(64, "Schema change has not been implemented."),
   If(65, "The state of the thread has been modified, and is now inconsistent."),
   Dz(
      66,
      "A direct superclass is different for the new class version, or the set of directly implemented interfaces is different and canUnrestrictedlyRedefineClasses is false."
   ),
   mI(67, "The new class version does not declare a method declared in the old class version and canUnrestrictedlyRedefineClasses is false."),
   jq(68, "A class file has a version number not supported by this VM."),
   ui(69, "The class name defined in the new class file is different from the name in the old class object."),
   TX(70, "The new class version has different modifiers and and canUnrestrictedlyRedefineClasses is false."),
   Rr(
      71,
      "A method in the new class version has different modifiers than its counterpart in the old class version and and canUnrestrictedlyRedefineClasses is false."
   ),
   EB(99, "The functionality is not implemented in this virtual machine."),
   Xo(100, "Invalid pointer."),
   Bu(101, "Desired information is not available."),
   IN(102, "The specified event type id is not recognized."),
   rL(103, "Illegal argument."),
   eJ(110, "The function needed to allocate memory and no more memory was available for allocation."),
   YN(111, "Debugging has not been enabled in this virtual machine. JVMTI cannot be used."),
   Rv(112, "The virtual machine is not running."),
   zx(113, "An unexpected internal error has occurred."),
   ZT(115, "The thread being used to call this function is not attached to the virtual machine. Calls must be made from attached threads."),
   Ri(500, "object type id or class tag."),
   GY(502, "Previous invoke not complete."),
   Wx(503, "Index is invalid."),
   AB(504, "The length is invalid."),
   CY(506, "The string is invalid."),
   WI(507, "The class loader is invalid."),
   Tq(508, "The array is invalid."),
   Yp(509, "Unable to load the transport."),
   Gu(510, "Unable to initialize the transport."),
   nY(511, "The method is native."),
   lF(512, "The count is invalid.");

   private final int nq;
   private final String NX;

   private FZ(int var3, String var4) {
      this.nq = var3;
      this.NX = var4;
   }

   public int q() {
      return this.nq;
   }

   public String RF() {
      return this.NX;
   }

   public static FZ q(int var0) {
      for (FZ var4 : values()) {
         if (var4.nq == var0) {
            return var4;
         }
      }

      throw new si("Invalid JDWP Error code: " + var0);
   }
}
