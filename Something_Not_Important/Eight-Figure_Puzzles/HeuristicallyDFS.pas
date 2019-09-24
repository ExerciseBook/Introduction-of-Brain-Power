{$MODE DELPHI}


Const
	StartStatus = longint(283164705);
	TargetStatus = longint(123804765);
	STargetStatus = string('123804765');
	MAXD = 6;
	
Var
	visitHistory : record
						p : array {[0..362880]} of longint;
						len : longint;
					end;
	
function VisitHistory_GetStatus(a:longint):longint;
Var
	i : longint;
Begin
	result := -1;
	for i:=0 to visitHistory.len-1 do begin
		if visitHistory.p[i]=a then begin
			result:=i;
			exit;
		end;
	end;
End;

function VisitHistory_SetStatus(a:longint;status:longint):longint;
Var
	Position : longint;
Begin
	Position := VisitHistory_GetStatus(a);
	if Position = -1 then begin
		if status=0 then exit();
		Position := visitHistory.len;
		inc(visitHistory.len);
		setLength(visitHistory.p,visitHistory.len);
		visitHistory.p[Position] := a;
		exit(1);
	end
	else
	begin
		if status<>0 then exit(0);
		visitHistory.p[Position] := 0;
	end;
End;

	// 1 2 3
	// 4 5 6
	// 7 8 9

function Move_Up(nowstatus:longint;Var nextstatus:longint):boolean;
Var
	s:string;
	p,q:longint;
Begin
	str(nowstatus,s);
	p:=pos('0',s);if p=0 then begin p:=1; s:='0'+s; end;
	if p<=3 then exit(false);
	q:=p-3;
	
	s[p]:=s[q];
	s[q]:='0';
	
	val(s,nextstatus);
	
	exit(true);
End;

function Move_Down(nowstatus:longint;Var nextstatus:longint):boolean;
Var
	s:string;
	p,q:longint;
Begin
	str(nowstatus,s);
	p:=pos('0',s);if p=0 then begin p:=1; s:='0'+s; end;
	if p>=7 then exit(false);
	q:=p+3;
	
	s[p]:=s[q];
	s[q]:='0';
	
	val(s,nextstatus);
	
	exit(true);
End;

function Move_Left(nowstatus:longint;Var nextstatus:longint):boolean;
Var
	s:string;
	p,q:longint;
Begin
	str(nowstatus,s);
	p:=pos('0',s);if p=0 then begin p:=1; s:='0'+s; end;
	if p mod 3 = 1 then exit(false);
	q:=p-1;
	
	s[p]:=s[q];
	s[q]:='0';
	
	val(s,nextstatus);
	
	exit(true);
End;

function Move_Right(nowstatus:longint;Var nextstatus:longint):boolean;
Var
	s:string;
	p,q:longint;
Begin
	str(nowstatus,s);
	p:=pos('0',s);if p=0 then begin p:=1; s:='0'+s; end;
	if p mod 3 = 0 then exit(false);
	q:=p+1;
	
	s[p]:=s[q];
	s[q]:='0';
	
	val(s,nextstatus);
	
	exit(true);
End;

function getWeight(a:longint):longint;
Var
	s:string;
	p:longint;
	i:longint;
Begin
	result:=0;
	str(a,s);
	p:=pos('0',s);if p=0 then begin p:=1; s:='0'+s; end;
	for i:=1 to 9 do
		if s[i]<>STargetStatus[i] then inc(result);
End;

function dfs(depth:longint;nowstatus:longint):longint;
type
	TNext = record
				v,w:longint;
			end;
Var
	nextstatus : longint;
	i,j : longint;
	
	l : longint;
	next : array of TNext;
	t : TNext;
Begin
	if depth > MAXD then exit(-1);

	for i:=1 to depth do write('	');
	writeln(nowstatus);

	if nowstatus=TargetStatus then begin
		exit(1);
	end;
	
	
	l := 0;
	//setlength(next,l);
	
	//up
	if Move_Up(nowstatus,nextstatus) then begin
		if VisitHistory_GetStatus(nextstatus)=-1 then begin
			VisitHistory_SetStatus(nextstatus,1);
			inc(l);
			setlength(next,l);
			next[l-1].v:=nextstatus;
			next[l-1].w:=getWeight(nextstatus);
			//if dfs(depth+1,nextstatus)=1 then exit;
			
		end;
	end;
	
	
	//right
	if Move_Right(nowstatus,nextstatus) then begin
		if VisitHistory_GetStatus(nextstatus)=-1 then begin
			VisitHistory_SetStatus(nextstatus,1);
			inc(l);
			setlength(next,l);
			next[l-1].v:=nextstatus;
			next[l-1].w:=getWeight(nextstatus);
		end;
	end;
	
	//down
	if Move_Down(nowstatus,nextstatus) then begin
		if VisitHistory_GetStatus(nextstatus)=-1 then begin
			VisitHistory_SetStatus(nextstatus,1);
			inc(l);
			setlength(next,l);
			next[l-1].v:=nextstatus;
			next[l-1].w:=getWeight(nextstatus);
		end;
	end;
	
	//left
	if Move_Left(nowstatus,nextstatus) then begin
		if VisitHistory_GetStatus(nextstatus)=-1 then begin
			VisitHistory_SetStatus(nextstatus,1);
			inc(l);
			setlength(next,l);
			next[l-1].v:=nextstatus;
			next[l-1].w:=getWeight(nextstatus);
		end;
	end;
	
	for i:=0 to l-1 do begin //后
		for j:=0 to i-1 do begin //前
			if next[i].w < next[j].w then begin
				t:=next[i];
				next[i]:=next[j];
				next[j]:=t;
			end;
		end;
	end;
	
	for i:=0 to l-1 do begin
		//if dfs(depth+1,next[i].v)=1 then exit(1);
		writeln(next[i].v,' ',next[i].w);
	end;
	
	for i:=0 to l-1 do begin
		if dfs(depth+1,next[i].v)=1 then exit(1);
	end;
	
	
	exit(0);
End;

Begin
	visitHistory.len:=0;
	VisitHistory_SetStatus(StartStatus,1);
	dfs(1,StartStatus);
End.