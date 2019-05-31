package Interfaces;

import Enums.SearchEngineName;

public interface EngineObserver {
	public void update( boolean finishToSearch ,SearchEngineName engineName );
}
