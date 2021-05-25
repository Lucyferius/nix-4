create table location
(
    id   integer generated always as identity
        constraint location_pkey
            primary key,
    name text not null
        constraint location_name_key
            unique
);

alter table location
    owner to lucy;

create table route
(
    id      integer generated always as identity
        constraint route_pkey
            primary key,
    id_from integer not null
        constraint route_id_from_fkey
            references location
            on update cascade on delete restrict,
    id_to   integer not null
        constraint route_id_to_fkey
            references location
            on update cascade on delete restrict,
    cost    integer not null
        constraint cost_high
            check (cost < 200000)
        constraint cost_low
            check (cost >= 0),
    constraint uniq_route_from_to
        unique (id_from, id_to)
);

alter table route
    owner to lucy;

create table problem
(
    id      integer generated always as identity
        constraint problem_pkey
            primary key,
    id_from integer not null
        constraint problem_id_from_fkey
            references location
            on update cascade on delete restrict,
    id_to   integer not null
        constraint problem_id_to_fkey
            references location
            on update cascade on delete restrict,
    constraint uniq_prob_from_to
        unique (id_from, id_to)
);

alter table problem
    owner to lucy;

create table solution
(
    problem_id integer not null
        constraint solution_pkey
            primary key
        constraint solution_problem_id_fkey
            references problem
            on update cascade on delete restrict,
    cost       integer
);

alter table solution
    owner to lucy;