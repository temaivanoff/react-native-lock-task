using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Lock.Task.RNLockTask
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNLockTaskModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNLockTaskModule"/>.
        /// </summary>
        internal RNLockTaskModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNLockTask";
            }
        }
    }
}
